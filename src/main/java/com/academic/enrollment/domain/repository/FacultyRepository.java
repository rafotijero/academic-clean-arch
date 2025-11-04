package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Faculty;
import com.academic.enrollment.domain.model.valueobjects.FacultyId;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository {
    Faculty save(Faculty faculty);

    Optional<Faculty> findById(FacultyId id);

    Optional<Faculty> findByName(String name);

    List<Faculty> findAll();

    List<Faculty> findActiveFaculties();

    void delete(FacultyId id);

    boolean existsByName(String name);
}
