package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.CreateFacultyUseCase;
import com.academic.enrollment.application.port.in.FindActiveFacultiesUseCase;
import com.academic.enrollment.application.port.in.DeleteFacultyUseCase;
import com.academic.enrollment.application.port.in.UpdateFacultyUseCase;
import com.academic.enrollment.application.dto.command.CreateFacultyCommand;
import com.academic.enrollment.application.dto.command.UpdateFacultyCommand;
import com.academic.enrollment.application.dto.response.FacultyResponse;
import com.academic.enrollment.application.mapper.FacultyMapper;
import com.academic.enrollment.domain.model.Faculty;
import com.academic.enrollment.domain.model.valueobjects.FacultyId;
import com.academic.enrollment.domain.repository.FacultyRepository;
import com.academic.enrollment.domain.exception.FacultyNotFoundException;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class FacultyApplicationService implements CreateFacultyUseCase,
                                                    FindActiveFacultiesUseCase,
                                                    UpdateFacultyUseCase,
                                                    DeleteFacultyUseCase {

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    public FacultyApplicationService(FacultyRepository facultyRepository,
                                      FacultyMapper facultyMapper) {
        this.facultyRepository = facultyRepository;
        this.facultyMapper = facultyMapper;
    }

    @Override
    public FacultyResponse create(CreateFacultyCommand command) {
        // Validate unique name
        if (facultyRepository.existsByName(command.getName())) {
            throw new IllegalArgumentException("Faculty with name " + command.getName() + " already exists");
        }

        // Create faculty
        Faculty faculty = Faculty.create(
                command.getName(),
                command.getDescription(),
                command.getLocation(),
                command.getDean()
        );

        // Save faculty
        Faculty savedFaculty = facultyRepository.save(faculty);

        // Convert to response DTO
        return facultyMapper.toResponse(savedFaculty, 0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacultyResponse> findActive() {
        List<Faculty> faculties = facultyRepository.findActiveFaculties();
        return faculties.stream()
                .map(faculty -> facultyMapper.toResponse(faculty, 0))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyResponse updateFaculty(Long id, UpdateFacultyCommand command) {
        Faculty faculty = facultyRepository.findById(new FacultyId(id))
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with ID: " + id));

        // Update faculty properties
        faculty.updateInfo(command.getDescription(), command.getLocation(), command.getDean());
        if (command.isActive()) {
            faculty.activate();
        } else {
            faculty.deactivate();
        }

        // Save updated faculty
        Faculty updatedFaculty = facultyRepository.save(faculty);

        return facultyMapper.toResponse(updatedFaculty, 0);
    }

    @Override
    public void deleteFaculty(Long id) {
        Faculty faculty = facultyRepository.findById(new FacultyId(id))
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with ID: " + id));
        faculty.deactivate(); // Soft delete
        facultyRepository.save(faculty);
    }
}
