package com.academic.enrollment.domain.exception;

public class ProfessorNotFoundException extends DomainException {
    public ProfessorNotFoundException(String professorId) {
        super("Professor not found with ID: " + professorId);
    }
}
