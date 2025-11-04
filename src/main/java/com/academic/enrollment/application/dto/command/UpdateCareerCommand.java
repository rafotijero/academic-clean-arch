package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCareerCommand {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

    @NotNull(message = "Duration in semesters is mandatory")
    @Positive(message = "Duration in semesters must be positive")
    private Integer durationSemesters;

    @Size(max = 100, message = "Degree awarded cannot be longer than 100 characters")
    private String degreeAwarded;

    private boolean active;
}
