package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateEnrollmentCommand {

    @NotNull(message = "Student ID is required")
    private String studentId;

    @NotNull(message = "Section ID is required")
    private String sectionId;

    @NotNull(message = "Cost is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Cost must be greater than zero")
    private BigDecimal cost;

    public CreateEnrollmentCommand() {}

    public CreateEnrollmentCommand(String studentId, String sectionId, BigDecimal cost) {
        this.studentId = studentId;
        this.sectionId = sectionId;
        this.cost = cost;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getSectionId() { return sectionId; }
    public void setSectionId(String sectionId) { this.sectionId = sectionId; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}
