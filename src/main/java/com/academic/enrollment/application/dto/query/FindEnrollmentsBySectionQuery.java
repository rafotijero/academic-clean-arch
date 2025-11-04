package com.academic.enrollment.application.dto.query;

import jakarta.validation.constraints.NotNull;

public class FindEnrollmentsBySectionQuery {

    @NotNull(message = "Section ID is required")
    private String sectionId;

    private String status;

    public FindEnrollmentsBySectionQuery() {}

    public FindEnrollmentsBySectionQuery(String sectionId, String status) {
        this.sectionId = sectionId;
        this.status = status;
    }

    public String getSectionId() { return sectionId; }
    public void setSectionId(String sectionId) { this.sectionId = sectionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
