package com.academic.enrollment.domain.service;

import com.academic.enrollment.domain.model.Enrollment;
import com.academic.enrollment.domain.model.Section;
import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.SectionId;
import com.academic.enrollment.domain.exception.EnrollmentConflictException;
import com.academic.enrollment.domain.exception.SectionCapacityExceededException;
import com.academic.enrollment.domain.repository.EnrollmentRepository;
import com.academic.enrollment.domain.repository.SectionRepository;

import java.util.List;

public class EnrollmentDomainService {

    private final EnrollmentRepository enrollmentRepository;
    private final SectionRepository sectionRepository;

    public EnrollmentDomainService(EnrollmentRepository enrollmentRepository,
                                   SectionRepository sectionRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.sectionRepository = sectionRepository;
    }

    public void validateEnrollment(StudentId studentId, SectionId sectionId) {
        if (enrollmentRepository.existsByStudentAndSection(studentId, sectionId)) {
            throw new EnrollmentConflictException(
                    "Student is already enrolled in this section"
            );
        }

        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));

        int currentEnrollments = enrollmentRepository.countBySectionId(sectionId);
        if (currentEnrollments >= section.getMaxCapacity()) {
            throw new SectionCapacityExceededException(
                    sectionId.toString(),
                    section.getMaxCapacity()
            );
        }
    }

    public boolean canEnroll(StudentId studentId, SectionId sectionId) {
        try {
            validateEnrollment(studentId, sectionId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getAvailableSeats(SectionId sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));

        int currentEnrollments = enrollmentRepository.countBySectionId(sectionId);
        return section.getMaxCapacity() - currentEnrollments;
    }

    public boolean isSectionFull(SectionId sectionId) {
        return getAvailableSeats(sectionId) <= 0;
    }
}
