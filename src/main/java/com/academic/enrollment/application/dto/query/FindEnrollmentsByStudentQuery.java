package com.academic.enrollment.application.dto.query;

import jakarta.validation.constraints.NotNull;

public class FindEnrollmentsByStudentQuery {

    @NotNull(message = "Student ID is required")
    private String studentId;

    private String academicPeriod;

    private String status;

    public FindEnrollmentsByStudentQuery() {}

    public FindEnrollmentsByStudentQuery(String studentId, String academicPeriod, String status) {
        this.studentId = studentId;
        this.academicPeriod = academicPeriod;
        this.status = status;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getAcademicPeriod() { return academicPeriod; }
    public void setAcademicPeriod(String academicPeriod) { this.academicPeriod = academicPeriod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
