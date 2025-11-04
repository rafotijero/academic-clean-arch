package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class SectionId {
    private final Long value;

    public SectionId(Long value) {
        this.value = Objects.requireNonNull(value, "Section ID cannot be null");
    }

    public static SectionId of(Long value) {
        return new SectionId(value);
    }

    public static SectionId of(String value) {
        return new SectionId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionId)) return false;
        SectionId sectionId = (SectionId) o;
        return Objects.equals(value, sectionId.value);
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
