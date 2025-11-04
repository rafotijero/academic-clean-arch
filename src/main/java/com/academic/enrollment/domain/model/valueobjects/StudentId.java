package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class StudentId {
    private final Long value;

    public StudentId(Long value) {
        this.value = Objects.requireNonNull(value, "Student ID cannot be null");
    }

    public static StudentId of(Long value) {
        return new StudentId(value);
    }

    public static StudentId of(String value) {
        return new StudentId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentId)) return false;
        StudentId studentId = (StudentId) o;
        return Objects.equals(value, studentId.value);
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
