package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class ProfessorId {
    private final Long value;

    public ProfessorId(Long value) {
        this.value = Objects.requireNonNull(value, "Professor ID cannot be null");
    }

    public static ProfessorId of(Long value) {
        return new ProfessorId(value);
    }

    public static ProfessorId of(String value) {
        return new ProfessorId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessorId)) return false;
        ProfessorId that = (ProfessorId) o;
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
