package com.academic.enrollment.application.dto.query;

import jakarta.validation.constraints.NotNull;

public class FindPaymentsByEnrollmentQuery {

    @NotNull(message = "Enrollment ID is required")
    private String enrollmentId;

    private String status;

    public FindPaymentsByEnrollmentQuery() {}

    public FindPaymentsByEnrollmentQuery(String enrollmentId, String status) {
        this.enrollmentId = enrollmentId;
        this.status = status;
    }

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
