package com.academic.enrollment.domain.model.event;

import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.StudentId;

import java.time.LocalDateTime;

public class EnrollmentPaidEvent implements DomainEvent {
    private final Long eventId;
    private final EnrollmentId enrollmentId;
    private final StudentId studentId;
    private final String paymentMethod;
    private final LocalDateTime occurredOn;

    public EnrollmentPaidEvent(EnrollmentId enrollmentId, StudentId studentId, String paymentMethod) {
        this.eventId = System.currentTimeMillis();
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.paymentMethod = paymentMethod;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public Long getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getEventType() {
        return "EnrollmentPaid";
    }

    public EnrollmentId getEnrollmentId() {
        return enrollmentId;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
