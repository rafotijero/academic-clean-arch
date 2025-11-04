package com.academic.enrollment.application.dto.response;

import java.time.LocalDate;

public class FacultyResponse {
    private String id;
    private String name;
    private String description;
    private String location;
    private String dean;
    private Integer totalCareers;
    private LocalDate registrationDate;
    private boolean active;

    public FacultyResponse() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDean() { return dean; }
    public void setDean(String dean) { this.dean = dean; }

    public Integer getTotalCareers() { return totalCareers; }
    public void setTotalCareers(Integer totalCareers) { this.totalCareers = totalCareers; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
