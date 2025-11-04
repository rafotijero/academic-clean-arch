package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.GradeRecord;
import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;

import java.util.List;
import java.util.Optional;

public interface GradeRecordRepository {
    GradeRecord save(GradeRecord gradeRecord);

    Optional<GradeRecord> findById(Long id);

    Optional<GradeRecord> findByEnrollmentId(EnrollmentId enrollmentId);

    List<GradeRecord> findAll();

    void delete(Long id);

    boolean existsByEnrollmentId(EnrollmentId enrollmentId);
}
