package com.academic.enrollment.domain.exception;

public class SectionNotFoundException extends DomainException {
    public SectionNotFoundException(String sectionId) {
        super("Section not found with ID: " + sectionId);
    }
}
