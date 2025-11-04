package com.academic.enrollment.domain.model.event;

import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.Email;

import java.time.LocalDateTime;

public class StudentRegisteredEvent implements DomainEvent {
    private final Long eventId;
    private final StudentId studentId;
    private final String fullName;
    private final Email email;
    private final LocalDateTime occurredOn;

    public StudentRegisteredEvent(StudentId studentId, String fullName, Email email) {
        this.eventId = System.currentTimeMillis();
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
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
        return "StudentRegistered";
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public Email getEmail() {
        return email;
    }
}
