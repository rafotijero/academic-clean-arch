package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class CareerId {
    private final Long value;

    public CareerId(Long value) {
        this.value = Objects.requireNonNull(value, "Career ID cannot be null");
    }

    public static CareerId of(Long value) {
        return new CareerId(value);
    }

    public static CareerId of(String value) {
        return new CareerId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CareerId)) return false;
        CareerId careerId = (CareerId) o;
        return Objects.equals(value, careerId.value);
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
