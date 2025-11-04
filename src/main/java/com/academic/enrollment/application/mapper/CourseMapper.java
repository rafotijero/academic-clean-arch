package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.CourseResponse;
import com.academic.enrollment.domain.model.Course;
import com.academic.enrollment.domain.model.Career;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponse toResponse(Course course, Career career) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId().toString());
        response.setCareerId(course.getCareerId().toString());
        response.setCareerName(career != null ? career.getName() : null);
        response.setCode(course.getCode().getValue());
        response.setName(course.getName());
        response.setDescription(course.getDescription());
        response.setCredits(course.getCredits().getValue());
        response.setSemesterLevel(course.getSemesterLevel());
        response.setRegistrationDate(course.getRegistrationDate());
        response.setActive(course.isActive());
        return response;
    }
}
