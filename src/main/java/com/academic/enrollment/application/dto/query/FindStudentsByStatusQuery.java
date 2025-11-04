package com.academic.enrollment.application.dto.query;

public class FindStudentsByStatusQuery {

    private Boolean active;

    public FindStudentsByStatusQuery() {}

    public FindStudentsByStatusQuery(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
