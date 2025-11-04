package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.CreateProfessorUseCase;
import com.academic.enrollment.application.port.in.FindProfessorsBySpecialtyUseCase;
import com.academic.enrollment.application.port.in.FindActiveProfessorsUseCase;
import com.academic.enrollment.application.dto.command.CreateProfessorCommand;
import com.academic.enrollment.application.dto.query.FindProfessorsBySpecialtyQuery;
import com.academic.enrollment.application.dto.response.ProfessorResponse;
import com.academic.enrollment.application.mapper.ProfessorMapper;
import com.academic.enrollment.domain.model.Professor;
import com.academic.enrollment.domain.model.valueobjects.DNI;
import com.academic.enrollment.domain.model.valueobjects.Email;
import com.academic.enrollment.domain.repository.ProfessorRepository;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class ProfessorApplicationService implements CreateProfessorUseCase,
                                                     FindProfessorsBySpecialtyUseCase,
                                                     FindActiveProfessorsUseCase {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorApplicationService(ProfessorRepository professorRepository,
                                        ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Override
    public ProfessorResponse create(CreateProfessorCommand command) {
        // Validate unique DNI
        DNI dni = new DNI(command.getDni());
        if (professorRepository.existsByDni(dni)) {
            throw new IllegalArgumentException("Professor with DNI " + command.getDni() + " already exists");
        }

        // Validate unique email
        Email email = new Email(command.getEmail());
        if (professorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Professor with email " + command.getEmail() + " already exists");
        }

        // Create professor
        Professor professor = Professor.create(
                command.getFirstName(),
                command.getLastName(),
                dni,
                email,
                command.getPhone(),
                command.getSpecialty(),
                command.getAcademicDegree()
        );

        // Save professor
        Professor savedProfessor = professorRepository.save(professor);

        // Convert to response DTO
        return professorMapper.toResponse(savedProfessor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfessorResponse> findBySpecialty(FindProfessorsBySpecialtyQuery query) {
        List<Professor> professors = professorRepository.findBySpecialty(query.getSpecialty());
        return professors.stream()
                .map(professorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfessorResponse> findActive() {
        List<Professor> professors = professorRepository.findActiveProfessors();
        return professors.stream()
                .map(professorMapper::toResponse)
                .collect(Collectors.toList());
    }
}
