package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateFacultyCommand {

    @NotBlank(message = "Faculty name is required")
    @Size(max = 100, message = "Faculty name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    @Size(max = 100, message = "Dean name must not exceed 100 characters")
    private String dean;

    public CreateFacultyCommand() {}

    public CreateFacultyCommand(String name, String description, String location, String dean) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.dean = dean;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDean() { return dean; }
    public void setDean(String dean) { this.dean = dean; }
}
