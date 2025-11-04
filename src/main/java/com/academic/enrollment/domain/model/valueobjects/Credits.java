package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class Credits {
    private static final int MIN_CREDITS = 1;
    private static final int MAX_CREDITS = 10;

    private final int value;

    public Credits(int value) {
        if (value < MIN_CREDITS) {
            throw new IllegalArgumentException("Credits must be at least " + MIN_CREDITS);
        }
        if (value > MAX_CREDITS) {
            throw new IllegalArgumentException("Credits cannot exceed " + MAX_CREDITS);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credits)) return false;
        Credits credits = (Credits) o;
        return value == credits.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
