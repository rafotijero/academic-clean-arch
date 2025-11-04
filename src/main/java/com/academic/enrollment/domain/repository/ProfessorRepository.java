package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Professor;
import com.academic.enrollment.domain.model.valueobjects.ProfessorId;
import com.academic.enrollment.domain.model.valueobjects.DNI;
import com.academic.enrollment.domain.model.valueobjects.Email;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {
    Professor save(Professor professor);

    Optional<Professor> findById(ProfessorId id);

    Optional<Professor> findByDni(DNI dni);

    Optional<Professor> findByEmail(Email email);

    List<Professor> findAll();

    List<Professor> findActiveProfessors();

    List<Professor> findBySpecialty(String specialty);

    void delete(ProfessorId id);

    boolean existsByDni(DNI dni);

    boolean existsByEmail(Email email);
}
