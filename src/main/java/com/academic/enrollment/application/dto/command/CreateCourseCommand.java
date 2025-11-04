package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class CreateCourseCommand {
    @NotBlank(message = "Career ID is required")
    private String careerId;

    @NotBlank(message = "Course code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    private String description;

    @NotNull(message = "Credits are required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits must not exceed 10")
    private Integer credits;

    @NotNull(message = "Semester level is required")
    @Min(value = 1, message = "Semester level must be at least 1")
    @Max(value = 20, message = "Semester level must not exceed 20")
    private Integer semesterLevel;

    public CreateCourseCommand() {}

    // Getters and Setters
    public String getCareerId() { return careerId; }
    public void setCareerId(String careerId) { this.careerId = careerId; }
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
}
