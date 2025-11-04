package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Enrollment;
import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.SectionId;
import com.academic.enrollment.domain.model.valueobjects.EnrollmentStatus;
import com.academic.enrollment.domain.model.valueobjects.AcademicPeriod;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);

    Optional<Enrollment> findById(EnrollmentId id);

    List<Enrollment> findAll();

    List<Enrollment> findByStudentId(StudentId studentId);

    List<Enrollment> findBySectionId(SectionId sectionId);

    List<Enrollment> findByStatus(EnrollmentStatus status);

    List<Enrollment> findByStudentAndPeriod(StudentId studentId, AcademicPeriod academicPeriod);

    Optional<Enrollment> findByStudentAndSection(StudentId studentId, SectionId sectionId);

    int countBySectionId(SectionId sectionId);

    void delete(EnrollmentId id);

    boolean existsByStudentAndSection(StudentId studentId, SectionId sectionId);
}
