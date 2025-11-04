package com.academic.enrollment.domain.model.event;

import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.CourseId;
import com.academic.enrollment.domain.model.valueobjects.Grade;

import java.time.LocalDateTime;

public class GradeRecordedEvent implements DomainEvent {
    private final Long eventId;
    private final EnrollmentId enrollmentId;
    private final StudentId studentId;
    private final CourseId courseId;
    private final Grade grade;
    private final LocalDateTime occurredOn;

    public GradeRecordedEvent(EnrollmentId enrollmentId, StudentId studentId,
                              CourseId courseId, Grade grade) {
        this.eventId = System.currentTimeMillis();
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
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
        return "GradeRecorded";
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

    public Grade getGrade() {
        return grade;
    }

    public boolean isPassing() {
        return grade.isPassing();
    }
}
