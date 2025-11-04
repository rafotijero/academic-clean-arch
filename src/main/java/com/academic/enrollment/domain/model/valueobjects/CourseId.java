package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class CourseId {
    private final Long value;

    public CourseId(Long value) {
        this.value = Objects.requireNonNull(value, "Course ID cannot be null");
    }

    public static CourseId of(Long value) {
        return new CourseId(value);
    }

    public static CourseId of(String value) {
        return new CourseId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseId)) return false;
        CourseId courseId = (CourseId) o;
        return Objects.equals(value, courseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
