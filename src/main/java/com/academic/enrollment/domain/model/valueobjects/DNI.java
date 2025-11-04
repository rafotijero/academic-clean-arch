package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class DNI {
    private final String value;

    public DNI(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI cannot be null or empty");
        }
        if (value.length() < 8 || value.length() > 20) {
            throw new IllegalArgumentException("DNI must be between 8 and 20 characters");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DNI)) return false;
        DNI dni = (DNI) o;
        return Objects.equals(value, dni.value);
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
