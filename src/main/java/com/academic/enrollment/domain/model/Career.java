package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.CareerId;
import com.academic.enrollment.domain.model.valueobjects.FacultyId;
import java.time.LocalDate;
import java.util.Objects;

public class Career {
    private final CareerId id;
    private final FacultyId facultyId;
    private String name;
    private String description;
    private int durationSemesters;
    private String degreeAwarded;
    private LocalDate registrationDate;
    private boolean active;

    public Career(CareerId id, FacultyId facultyId, String name, String description,
                  int durationSemesters, String degreeAwarded, LocalDate registrationDate) {
        this.id = id;
        this.facultyId = Objects.requireNonNull(facultyId, "Faculty ID cannot be null");
        this.setName(name);
        this.description = description;
        this.setDurationSemesters(durationSemesters);
        this.degreeAwarded = degreeAwarded;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
        this.active = true;
    }

    public static Career create(FacultyId facultyId, String name, String description,
                                int durationSemesters, String degreeAwarded) {
        return new Career(null, facultyId, name, description,
                durationSemesters, degreeAwarded, LocalDate.now());
    }

    public void updateInfo(String description, int durationSemesters, String degreeAwarded) {
        this.description = description;
        this.setDurationSemesters(durationSemesters);
        this.degreeAwarded = degreeAwarded;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Career name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Career name cannot exceed 100 characters");
        }
        this.name = name.trim();
    }

    private void setDurationSemesters(int durationSemesters) {
        if (durationSemesters <= 0) {
            throw new IllegalArgumentException("Duration semesters must be positive");
        }
        if (durationSemesters > 20) {
            throw new IllegalArgumentException("Duration semesters cannot exceed 20");
        }
        this.durationSemesters = durationSemesters;
    }

    // Getters
    public CareerId getId() { return id; }
    public FacultyId getFacultyId() { return facultyId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getDurationSemesters() { return durationSemesters; }
    public String getDegreeAwarded() { return degreeAwarded; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Career)) return false;
        Career career = (Career) o;
        return Objects.equals(id, career.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
