package com.academic.enrollment.domain.service;

import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.CourseId;
import com.academic.enrollment.domain.exception.PrerequisiteNotMetException;

import java.util.List;

public class PrerequisiteValidationService {


    public void validatePrerequisites(StudentId studentId, CourseId courseId) {

    }

    public boolean hasMetPrerequisites(StudentId studentId, CourseId courseId) {
        try {
            validatePrerequisites(studentId, courseId);
            return true;
        } catch (PrerequisiteNotMetException e) {
            return false;
        }
    }

    public List<CourseId> getMissingPrerequisites(StudentId studentId, CourseId courseId) {
        return List.of();
    }
}
