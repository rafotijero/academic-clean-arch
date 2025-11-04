package com.academic.enrollment.domain.model.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Grade {
    private static final BigDecimal MIN_GRADE = BigDecimal.ZERO;
    private static final BigDecimal MAX_GRADE = new BigDecimal("20.00");
    private static final BigDecimal PASSING_GRADE = new BigDecimal("11.00");

    private final BigDecimal value;

    public Grade(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }
        if (value.compareTo(MIN_GRADE) < 0) {
            throw new IllegalArgumentException("Grade cannot be negative");
        }
        if (value.compareTo(MAX_GRADE) > 0) {
            throw new IllegalArgumentException("Grade cannot exceed " + MAX_GRADE);
        }
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Grade of(double value) {
        return new Grade(BigDecimal.valueOf(value));
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isPassing() {
        return value.compareTo(PASSING_GRADE) >= 0;
    }

    public boolean isFailing() {
        return !isPassing() && value.compareTo(MIN_GRADE) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade grade = (Grade) o;
        return Objects.equals(value, grade.value);
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
