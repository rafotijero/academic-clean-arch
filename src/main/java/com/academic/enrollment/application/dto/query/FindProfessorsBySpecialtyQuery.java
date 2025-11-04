package com.academic.enrollment.application.dto.query;

public class FindProfessorsBySpecialtyQuery {
    private String specialty;

    public FindProfessorsBySpecialtyQuery() {
    }

    public FindProfessorsBySpecialtyQuery(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
