package com.academic.enrollment.application.dto.response;

import java.time.LocalDate;

public class CourseResponse {
    private String id;
    private String careerId;
    private String careerName;
    private String code;
    private String name;
    private String description;
    private Integer credits;
    private Integer semesterLevel;
    private LocalDate registrationDate;
    private boolean active;

    public CourseResponse() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCareerId() { return careerId; }
    public void setCareerId(String careerId) { this.careerId = careerId; }

    public String getCareerName() { return careerName; }
    public void setCareerName(String careerName) { this.careerName = careerName; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public Integer getSemesterLevel() { return semesterLevel; }
    public void setSemesterLevel(Integer semesterLevel) { this.semesterLevel = semesterLevel; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
