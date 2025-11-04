package com.academic.enrollment.application.dto.query;
public class FindCareersByFacultyQuery {
    private String facultyId;
    public FindCareersByFacultyQuery() {}
    public FindCareersByFacultyQuery(String facultyId) { this.facultyId = facultyId; }
    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }
}
