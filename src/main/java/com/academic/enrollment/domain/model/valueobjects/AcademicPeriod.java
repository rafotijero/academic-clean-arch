package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public class AcademicPeriod {
    private static final Pattern PERIOD_PATTERN = Pattern.compile("^\\d{4}-[12]$");

    private final String value;

    public AcademicPeriod(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic period cannot be null or empty");
        }
        if (!PERIOD_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid academic period format. Expected format: YYYY-1 or YYYY-2");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    public int getYear() {
        return Integer.parseInt(value.substring(0, 4));
    }

    public int getSemester() {
        return Integer.parseInt(value.substring(5));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcademicPeriod)) return false;
        AcademicPeriod that = (AcademicPeriod) o;
        return Objects.equals(value, that.value);
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
