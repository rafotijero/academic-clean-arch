package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.CourseId;
import com.academic.enrollment.domain.model.valueobjects.CareerId;
import com.academic.enrollment.domain.model.valueobjects.CourseCode;
import com.academic.enrollment.domain.model.valueobjects.Credits;
import java.time.LocalDate;
import java.util.Objects;

public class Course {
    private final CourseId id;
    private final CareerId careerId;
    private final CourseCode code;
    private String name;
    private String description;
    private Credits credits;
    private int semesterLevel;
    private LocalDate registrationDate;
    private boolean active;

    public Course(CourseId id, CareerId careerId, CourseCode code, String name,
                  String description, Credits credits, int semesterLevel, LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "Course ID cannot be null");
        this.careerId = Objects.requireNonNull(careerId, "Career ID cannot be null");
        this.code = Objects.requireNonNull(code, "Course code cannot be null");
        this.credits = Objects.requireNonNull(credits, "Credits cannot be null");
        this.setName(name);
        this.description = description;
        this.setSemesterLevel(semesterLevel);
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
        this.active = true;
    }

    public static Course create(CareerId careerId, CourseCode code, String name,
                                String description, Credits credits, int semesterLevel) {
        return new Course(null, careerId, code, name, description,
                credits, semesterLevel, LocalDate.now());
    }

    public void updateInfo(String name, String description, Credits credits) {
        this.setName(name);
        this.description = description;
        this.credits = Objects.requireNonNull(credits, "Credits cannot be null");
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Course name cannot exceed 100 characters");
        }
        this.name = name.trim();
    }

    private void setSemesterLevel(int semesterLevel) {
        if (semesterLevel <= 0) {
            throw new IllegalArgumentException("Semester level must be positive");
        }
        if (semesterLevel > 20) {
            throw new IllegalArgumentException("Semester level cannot exceed 20");
        }
        this.semesterLevel = semesterLevel;
    }

    // Getters
    public CourseId getId() { return id; }
    public CareerId getCareerId() { return careerId; }
    public CourseCode getCode() { return code; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Credits getCredits() { return credits; }
    public int getSemesterLevel() { return semesterLevel; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
