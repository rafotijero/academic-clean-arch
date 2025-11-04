package com.academic.enrollment.shared.exception;

/**
 * Base exception for business logic errors
 * These are expected errors that occur during normal business operations
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
