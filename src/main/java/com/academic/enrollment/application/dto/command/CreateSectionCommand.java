package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class CreateSectionCommand {

    @NotNull(message = "Course ID is required")
    private String courseId;

    @NotNull(message = "Professor ID is required")
    private String professorId;

    @NotBlank(message = "Section code is required")
    @Size(max = 20, message = "Section code must not exceed 20 characters")
    private String code;

    @NotNull(message = "Max capacity is required")
    @Min(value = 1, message = "Max capacity must be at least 1")
    @Max(value = 100, message = "Max capacity must not exceed 100")
    private Integer maxCapacity;

    @Size(max = 50, message = "Classroom must not exceed 50 characters")
    private String classroom;

    @Size(max = 100, message = "Schedule must not exceed 100 characters")
    private String schedule;

    @Size(max = 50, message = "Days must not exceed 50 characters")
    private String days;

    @NotBlank(message = "Academic period is required")
    private String academicPeriod;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    public CreateSectionCommand() {}

    public CreateSectionCommand(String courseId, String professorId, String code,
                                Integer maxCapacity, String classroom, String schedule,
                                String days, String academicPeriod, LocalDate startDate,
                                LocalDate endDate) {
        this.courseId = courseId;
        this.professorId = professorId;
        this.code = code;
        this.maxCapacity = maxCapacity;
        this.classroom = classroom;
        this.schedule = schedule;
        this.days = days;
        this.academicPeriod = academicPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getProfessorId() { return professorId; }
    public void setProfessorId(String professorId) { this.professorId = professorId; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Integer getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(Integer maxCapacity) { this.maxCapacity = maxCapacity; }

    public String getClassroom() { return classroom; }
    public void setClassroom(String classroom) { this.classroom = classroom; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public String getDays() { return days; }
    public void setDays(String days) { this.days = days; }

    public String getAcademicPeriod() { return academicPeriod; }
    public void setAcademicPeriod(String academicPeriod) { this.academicPeriod = academicPeriod; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
