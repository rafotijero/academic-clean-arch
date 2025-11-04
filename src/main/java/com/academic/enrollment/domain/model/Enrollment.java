package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.*;
import com.academic.enrollment.domain.exception.InvalidEnrollmentException;
import java.time.LocalDate;
import java.util.Objects;

public class Enrollment {
    private final EnrollmentId id;
    private final StudentId studentId;
    private final SectionId sectionId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;
    private Money cost;
    private String paymentMethod;
    private LocalDate registrationDate;

    public Enrollment(EnrollmentId id, StudentId studentId, SectionId sectionId,
                      LocalDate enrollmentDate, EnrollmentStatus status, Money cost,
                      String paymentMethod, LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "Enrollment ID cannot be null");
        this.studentId = Objects.requireNonNull(studentId, "Student ID cannot be null");
        this.sectionId = Objects.requireNonNull(sectionId, "Section ID cannot be null");
        this.cost = Objects.requireNonNull(cost, "Cost cannot be null");
        this.enrollmentDate = enrollmentDate != null ? enrollmentDate : LocalDate.now();
        this.status = status != null ? status : EnrollmentStatus.PENDIENTE;
        this.paymentMethod = paymentMethod;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
    }

    public static Enrollment create(StudentId studentId, SectionId sectionId, Money cost) {
        return new Enrollment(null, studentId, sectionId,
                LocalDate.now(), EnrollmentStatus.PENDIENTE, cost, null, LocalDate.now());
    }

    public void markAsPaid(String paymentMethod) {
        if (status != EnrollmentStatus.PENDIENTE) {
            throw new InvalidEnrollmentException("Only pending enrollments can be marked as paid");
        }
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        this.status = EnrollmentStatus.PAGADO;
        this.paymentMethod = paymentMethod;
    }

    public void complete() {
        if (status != EnrollmentStatus.PAGADO) {
            throw new InvalidEnrollmentException("Only paid enrollments can be completed");
        }
        this.status = EnrollmentStatus.COMPLETADO;
    }

    public void cancel() {
        if (!status.canBeCancelled()) {
            throw new InvalidEnrollmentException("Cannot cancel enrollment with status: " + status);
        }
        this.status = EnrollmentStatus.ANULADO;
    }

    // Getters
    public EnrollmentId getId() { return id; }
    public StudentId getStudentId() { return studentId; }
    public SectionId getSectionId() { return sectionId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public EnrollmentStatus getStatus() { return status; }
    public Money getCost() { return cost; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDate getRegistrationDate() { return registrationDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
