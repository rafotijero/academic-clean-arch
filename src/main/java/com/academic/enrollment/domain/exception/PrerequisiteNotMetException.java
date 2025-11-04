package com.academic.enrollment.domain.exception;

public class PrerequisiteNotMetException extends DomainException {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }

    public PrerequisiteNotMetException(String courseCode, String prerequisiteCourseCode) {
        super("Cannot enroll in course " + courseCode + " without completing prerequisite: " + prerequisiteCourseCode);
    }
}
