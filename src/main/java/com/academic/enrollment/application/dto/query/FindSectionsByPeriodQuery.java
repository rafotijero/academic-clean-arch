package com.academic.enrollment.application.dto.query;

import jakarta.validation.constraints.NotBlank;

public class FindSectionsByPeriodQuery {

    @NotBlank(message = "Academic period is required")
    private String academicPeriod;

    private String courseId;

    private String professorId;

    private Boolean active;

    public FindSectionsByPeriodQuery() {}

    public FindSectionsByPeriodQuery(String academicPeriod, String courseId,
                                     String professorId, Boolean active) {
        this.academicPeriod = academicPeriod;
        this.courseId = courseId;
        this.professorId = professorId;
        this.active = active;
    }

    public String getAcademicPeriod() { return academicPeriod; }
    public void setAcademicPeriod(String academicPeriod) { this.academicPeriod = academicPeriod; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getProfessorId() { return professorId; }
    public void setProfessorId(String professorId) { this.professorId = professorId; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
