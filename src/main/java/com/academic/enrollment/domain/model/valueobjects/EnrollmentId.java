package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class EnrollmentId {
    private final Long value;

    public EnrollmentId(Long value) {
        this.value = Objects.requireNonNull(value, "Enrollment ID cannot be null");
    }

    public static EnrollmentId of(Long value) {
        return new EnrollmentId(value);
    }

    public static EnrollmentId of(String value) {
        return new EnrollmentId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentId)) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(value, that.value);
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
