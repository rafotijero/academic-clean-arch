package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.FacultyId;
import java.time.LocalDate;
import java.util.Objects;

public class Faculty {
    private final FacultyId id;
    private String name;
    private String description;
    private String location;
    private String dean;
    private LocalDate registrationDate;
    private boolean active;

    public Faculty(FacultyId id, String name, String description, String location,
                   String dean, LocalDate registrationDate) {
        this.id = id;
        this.setName(name);
        this.description = description;
        this.location = location;
        this.dean = dean;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
        this.active = true;
    }

    public static Faculty create(String name, String description, String location, String dean) {
        return new Faculty(null, name, description, location, dean, LocalDate.now());
    }

    public void updateInfo(String description, String location, String dean) {
        this.description = description;
        this.location = location;
        this.dean = dean;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Faculty name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Faculty name cannot exceed 100 characters");
        }
        this.name = name.trim();
    }

    // Getters
    public FacultyId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDean() { return dean; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
