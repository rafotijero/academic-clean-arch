package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Course;
import com.academic.enrollment.domain.model.valueobjects.CourseId;
import com.academic.enrollment.domain.model.valueobjects.CareerId;
import com.academic.enrollment.domain.model.valueobjects.CourseCode;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);

    Optional<Course> findById(CourseId id);

    Optional<Course> findByCode(CourseCode code);

    List<Course> findAll();

    List<Course> findActiveCourses();

    List<Course> findByCareerId(CareerId careerId);

    List<Course> findBySemesterLevel(int semesterLevel);

    void delete(CourseId id);

    boolean existsByCode(CourseCode code);
}
