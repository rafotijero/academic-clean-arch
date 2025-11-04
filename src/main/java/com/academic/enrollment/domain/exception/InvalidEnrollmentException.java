package com.academic.enrollment.domain.exception;

public class InvalidEnrollmentException extends DomainException {
    public InvalidEnrollmentException(String message) {
        super(message);
    }

    public InvalidEnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
