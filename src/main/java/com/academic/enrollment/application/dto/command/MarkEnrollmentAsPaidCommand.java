package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MarkEnrollmentAsPaidCommand {

    @NotNull(message = "Enrollment ID is required")
    private String enrollmentId;

    @NotBlank(message = "Payment method is required")
    @Size(max = 50, message = "Payment method must not exceed 50 characters")
    private String paymentMethod;

    public MarkEnrollmentAsPaidCommand() {}

    public MarkEnrollmentAsPaidCommand(String enrollmentId, String paymentMethod) {
        this.enrollmentId = enrollmentId;
        this.paymentMethod = paymentMethod;
    }

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
