package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateFacultyCommand {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

    @Size(max = 100, message = "Location cannot be longer than 100 characters")
    private String location;

    @Size(max = 100, message = "Dean cannot be longer than 100 characters")
    private String dean;

    private boolean active;
}
