package com.academic.enrollment.shared.exception;

/**
 * Base exception for technical errors
 * These are unexpected errors related to infrastructure, database, network, etc.
 */
public class TechnicalException extends RuntimeException {

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
