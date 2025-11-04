package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.Grade;
import java.time.LocalDate;
import java.util.Objects;

public class GradeRecord {
    private final Long id;
    private final EnrollmentId enrollmentId;
    private Grade grade;
    private String observation;
    private LocalDate registrationDate;

    public GradeRecord(Long id, EnrollmentId enrollmentId, Grade grade,
                       String observation, LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "Grade record ID cannot be null");
        this.enrollmentId = Objects.requireNonNull(enrollmentId, "Enrollment ID cannot be null");
        this.grade = grade;
        this.observation = observation;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
    }

    public static GradeRecord create(EnrollmentId enrollmentId, Grade grade, String observation) {
        return new GradeRecord(null, enrollmentId, grade, observation, LocalDate.now());
    }

    public void updateGrade(Grade newGrade, String observation) {
        this.grade = newGrade;
        this.observation = observation;
    }

    public boolean isPassing() {
        return grade != null && grade.isPassing();
    }

    public boolean isFailing() {
        return grade != null && grade.isFailing();
    }

    // Getters
    public Long getId() { return id; }
    public EnrollmentId getEnrollmentId() { return enrollmentId; }
    public Grade getGrade() { return grade; }
    public String getObservation() { return observation; }
    public LocalDate getRegistrationDate() { return registrationDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeRecord)) return false;
        GradeRecord that = (GradeRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
