package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCareerCommand {

    @NotNull(message = "Faculty ID is required")
    private String facultyId;

    @NotBlank(message = "Career name is required")
    @Size(max = 100, message = "Career name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Duration in semesters is required")
    @Min(value = 1, message = "Duration must be at least 1 semester")
    @Max(value = 20, message = "Duration must not exceed 20 semesters")
    private Integer durationSemesters;

    @Size(max = 100, message = "Degree awarded must not exceed 100 characters")
    private String degreeAwarded;

    public CreateCareerCommand() {}

    public CreateCareerCommand(String facultyId, String name, String description,
                               Integer durationSemesters, String degreeAwarded) {
        this.facultyId = facultyId;
        this.name = name;
        this.description = description;
        this.durationSemesters = durationSemesters;
        this.degreeAwarded = degreeAwarded;
    }

    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDurationSemesters() { return durationSemesters; }
    public void setDurationSemesters(Integer durationSemesters) { this.durationSemesters = durationSemesters; }

    public String getDegreeAwarded() { return degreeAwarded; }
    public void setDegreeAwarded(String degreeAwarded) { this.degreeAwarded = degreeAwarded; }
}
