package com.academic.enrollment.domain.model.event;

import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.CourseId;

import java.time.LocalDateTime;

public class EnrollmentCompletedEvent implements DomainEvent {
    private final Long eventId;
    private final EnrollmentId enrollmentId;
    private final StudentId studentId;
    private final CourseId courseId;
    private final LocalDateTime occurredOn;

    public EnrollmentCompletedEvent(EnrollmentId enrollmentId, StudentId studentId, CourseId courseId) {
        this.eventId = System.currentTimeMillis();
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
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
        return "EnrollmentCompleted";
    }

    public EnrollmentId getEnrollmentId() {
        return enrollmentId;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public CourseId getCourseId() {
        return courseId;
    }
}
