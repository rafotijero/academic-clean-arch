package com.academic.enrollment.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public class FacultyId implements Serializable {
    private final Long value;

    public FacultyId(Long value) {
        this.value = Objects.requireNonNull(value, "Faculty ID cannot be null");
    }

    public static FacultyId of(Long value) {
        return new FacultyId(value);
    }

    public static FacultyId of(String value) {
        return new FacultyId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyId)) return false;
        FacultyId facultyId = (FacultyId) o;
        return Objects.equals(value, facultyId.value);
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
