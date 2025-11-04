package com.academic.enrollment.domain.exception;

public class CourseNotFoundException extends DomainException {
    public CourseNotFoundException(String courseId) {
        super("Course not found with ID: " + courseId);
    }
}
