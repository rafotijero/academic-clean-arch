package com.academic.enrollment.domain.exception;

public class SectionCapacityExceededException extends DomainException {
    public SectionCapacityExceededException(String message) {
        super(message);
    }

    public SectionCapacityExceededException(String sectionId, int maxCapacity) {
        super("Section " + sectionId + " has reached its maximum capacity of " + maxCapacity);
    }
}
