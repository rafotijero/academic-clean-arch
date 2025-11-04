package com.academic.enrollment.application.dto.response;

import java.time.LocalDate;

public class CareerResponse {
    private String id;
    private String facultyId;
    private String facultyName;
    private String name;
    private String description;
    private Integer durationSemesters;
    private String degreeAwarded;
    private Integer totalCourses;
    private LocalDate registrationDate;
    private boolean active;

    public CareerResponse() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDurationSemesters() { return durationSemesters; }
    public void setDurationSemesters(Integer durationSemesters) { this.durationSemesters = durationSemesters; }

    public String getDegreeAwarded() { return degreeAwarded; }
    public void setDegreeAwarded(String degreeAwarded) { this.degreeAwarded = degreeAwarded; }

    public Integer getTotalCourses() { return totalCourses; }
    public void setTotalCourses(Integer totalCourses) { this.totalCourses = totalCourses; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
