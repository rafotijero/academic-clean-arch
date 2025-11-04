package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.CreateCareerUseCase;
import com.academic.enrollment.application.port.in.FindCareersByFacultyUseCase;
import com.academic.enrollment.application.port.in.DeleteCareerUseCase;
import com.academic.enrollment.application.port.in.UpdateCareerUseCase;
import com.academic.enrollment.application.dto.command.CreateCareerCommand;
import com.academic.enrollment.application.dto.command.UpdateCareerCommand;
import com.academic.enrollment.application.dto.query.FindCareersByFacultyQuery;
import com.academic.enrollment.application.dto.response.CareerResponse;
import com.academic.enrollment.application.mapper.CareerMapper;
import com.academic.enrollment.domain.model.Career;
import com.academic.enrollment.domain.model.valueobjects.CareerId;
import com.academic.enrollment.domain.model.valueobjects.FacultyId;
import com.academic.enrollment.domain.repository.CareerRepository;
import com.academic.enrollment.domain.exception.CareerNotFoundException;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class CareerApplicationService implements CreateCareerUseCase,
                                                   FindCareersByFacultyUseCase,
                                                   UpdateCareerUseCase,
                                                   DeleteCareerUseCase {

    private final CareerRepository careerRepository;
    private final CareerMapper careerMapper;

    public CareerApplicationService(CareerRepository careerRepository,
                                     CareerMapper careerMapper) {
        this.careerRepository = careerRepository;
        this.careerMapper = careerMapper;
    }

    @Override
    public CareerResponse create(CreateCareerCommand command) {
        // Validate unique name
        if (careerRepository.existsByName(command.getName())) {
            throw new IllegalArgumentException("Career with name " + command.getName() + " already exists");
        }

        // Create career
        Career career = Career.create(
                FacultyId.of(command.getFacultyId()),
                command.getName(),
                command.getDescription(),
                command.getDurationSemesters(),
                command.getDegreeAwarded()
        );

        // Save career
        Career savedCareer = careerRepository.save(career);

        // Convert to response DTO
        return careerMapper.toResponse(savedCareer, null, 0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CareerResponse> findByFacultyId(FindCareersByFacultyQuery query) {
        List<Career> careers = careerRepository.findByFacultyId(FacultyId.of(query.getFacultyId()));
        return careers.stream()
                .map(career -> careerMapper.toResponse(career, null, 0))
                .collect(Collectors.toList());
    }

    @Override
    public CareerResponse updateCareer(Long id, UpdateCareerCommand command) {
        Career career = careerRepository.findById(new CareerId(id))
                .orElseThrow(() -> new CareerNotFoundException("Career not found with ID: " + id));

        // Update career properties
        career.updateInfo(command.getDescription(), command.getDurationSemesters(), command.getDegreeAwarded());
        if (command.isActive()) {
            career.activate();
        } else {
            career.deactivate();
        }

        // Save updated career
        Career updatedCareer = careerRepository.save(career);

        return careerMapper.toResponse(updatedCareer, null, 0);
    }

    @Override
    public void deleteCareer(Long id) {
        Career career = careerRepository.findById(new CareerId(id))
                .orElseThrow(() -> new CareerNotFoundException("Career not found with ID: " + id));
        career.deactivate(); // Soft delete
        careerRepository.save(career);
    }
}
