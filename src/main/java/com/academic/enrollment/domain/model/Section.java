package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.SectionId;
import com.academic.enrollment.domain.model.valueobjects.CourseId;
import com.academic.enrollment.domain.model.valueobjects.ProfessorId;
import com.academic.enrollment.domain.model.valueobjects.AcademicPeriod;
import java.time.LocalDate;
import java.util.Objects;

public class Section {
    private final SectionId id;
    private final CourseId courseId;
    private ProfessorId professorId;
    private String code;
    private int maxCapacity;
    private String classroom;
    private String schedule;
    private String days;
    private AcademicPeriod academicPeriod;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationDate;
    private boolean active;

    public Section(SectionId id, CourseId courseId, ProfessorId professorId, String code,
                   int maxCapacity, String classroom, String schedule, String days,
                   AcademicPeriod academicPeriod, LocalDate startDate, LocalDate endDate,
                   LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "Section ID cannot be null");
        this.courseId = Objects.requireNonNull(courseId, "Course ID cannot be null");
        this.professorId = Objects.requireNonNull(professorId, "Professor ID cannot be null");
        this.academicPeriod = Objects.requireNonNull(academicPeriod, "Academic period cannot be null");
        this.setCode(code);
        this.setMaxCapacity(maxCapacity);
        this.classroom = classroom;
        this.schedule = schedule;
        this.days = days;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
        this.active = true;
    }

    public static Section create(CourseId courseId, ProfessorId professorId, String code,
                                 int maxCapacity, String classroom, String schedule, String days,
                                 AcademicPeriod academicPeriod, LocalDate startDate, LocalDate endDate) {
        return new Section(null, courseId, professorId, code, maxCapacity,
                classroom, schedule, days, academicPeriod, startDate, endDate, LocalDate.now());
    }

    public void updateSchedule(String classroom, String schedule, String days) {
        this.classroom = classroom;
        this.schedule = schedule;
        this.days = days;
    }

    public void changeProfessor(ProfessorId newProfessorId) {
        this.professorId = Objects.requireNonNull(newProfessorId, "Professor ID cannot be null");
    }

    public void updateDates(LocalDate startDate, LocalDate endDate) {
        if (endDate != null && startDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    private void setCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Section code cannot be null or empty");
        }
        if (code.length() > 20) {
            throw new IllegalArgumentException("Section code cannot exceed 20 characters");
        }
        this.code = code.trim().toUpperCase();
    }

    private void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be positive");
        }
        if (maxCapacity > 100) {
            throw new IllegalArgumentException("Max capacity cannot exceed 100");
        }
        this.maxCapacity = maxCapacity;
    }

    // Getters
    public SectionId getId() { return id; }
    public CourseId getCourseId() { return courseId; }
    public ProfessorId getProfessorId() { return professorId; }
    public String getCode() { return code; }
    public int getMaxCapacity() { return maxCapacity; }
    public String getClassroom() { return classroom; }
    public String getSchedule() { return schedule; }
    public String getDays() { return days; }
    public AcademicPeriod getAcademicPeriod() { return academicPeriod; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        Section section = (Section) o;
        return Objects.equals(id, section.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
