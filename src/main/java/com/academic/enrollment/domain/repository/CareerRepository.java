package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Career;
import com.academic.enrollment.domain.model.valueobjects.CareerId;
import com.academic.enrollment.domain.model.valueobjects.FacultyId;

import java.util.List;
import java.util.Optional;

public interface CareerRepository {
    Career save(Career career);

    Optional<Career> findById(CareerId id);

    Optional<Career> findByName(String name);

    List<Career> findAll();

    List<Career> findActiveCareers();

    List<Career> findByFacultyId(FacultyId facultyId);

    void delete(CareerId id);

    boolean existsByName(String name);
}
