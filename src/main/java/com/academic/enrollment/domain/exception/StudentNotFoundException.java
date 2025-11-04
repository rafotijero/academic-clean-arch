package com.academic.enrollment.domain.exception;

public class StudentNotFoundException extends DomainException {
    public StudentNotFoundException(String studentId) {
        super("Student not found with ID: " + studentId);
    }
}
