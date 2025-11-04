package com.academic.enrollment.domain.model.event;

import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.SectionId;
import com.academic.enrollment.domain.model.valueobjects.Money;

import java.time.LocalDateTime;

public class EnrollmentCreatedEvent implements DomainEvent {
    private final Long eventId;
    private final EnrollmentId enrollmentId;
    private final StudentId studentId;
    private final SectionId sectionId;
    private final Money cost;
    private final LocalDateTime occurredOn;

    public EnrollmentCreatedEvent(EnrollmentId enrollmentId, StudentId studentId,
                                  SectionId sectionId, Money cost) {
        this.eventId = System.currentTimeMillis();
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.sectionId = sectionId;
        this.cost = cost;
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
        return "EnrollmentCreated";
    }

    public EnrollmentId getEnrollmentId() {
        return enrollmentId;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public SectionId getSectionId() {
        return sectionId;
    }

    public Money getCost() {
        return cost;
    }
}
