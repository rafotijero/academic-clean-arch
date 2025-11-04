package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class RecordGradeCommand {

    @NotNull(message = "Enrollment ID is required")
    private String enrollmentId;

    @NotNull(message = "Grade is required")
    @DecimalMin(value = "0", message = "Grade must be at least 0")
    @DecimalMax(value = "20", message = "Grade must not exceed 20")
    private BigDecimal grade;

    @Size(max = 500, message = "Observation must not exceed 500 characters")
    private String observation;

    public RecordGradeCommand() {}

    public RecordGradeCommand(String enrollmentId, BigDecimal grade, String observation) {
        this.enrollmentId = enrollmentId;
        this.grade = grade;
        this.observation = observation;
    }

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }

    public BigDecimal getGrade() { return grade; }
    public void setGrade(BigDecimal grade) { this.grade = grade; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }
}
