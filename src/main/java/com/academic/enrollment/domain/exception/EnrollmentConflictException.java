package com.academic.enrollment.domain.exception;

public class EnrollmentConflictException extends DomainException {
    public EnrollmentConflictException(String message) {
        super(message);
    }

    public EnrollmentConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
