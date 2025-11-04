package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class CourseCode {
    private final String value;

    public CourseCode(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty");
        }
        if (value.length() > 20) {
            throw new IllegalArgumentException("Course code cannot exceed 20 characters");
        }
        this.value = value.trim().toUpperCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseCode)) return false;
        CourseCode that = (CourseCode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
