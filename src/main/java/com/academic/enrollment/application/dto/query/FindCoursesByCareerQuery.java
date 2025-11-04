package com.academic.enrollment.application.dto.query;
public class FindCoursesByCareerQuery {
    private String careerId;
    public FindCoursesByCareerQuery() {}
    public FindCoursesByCareerQuery(String careerId) { this.careerId = careerId; }
    public String getCareerId() { return careerId; }
    public void setCareerId(String careerId) { this.careerId = careerId; }
}
