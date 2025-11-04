package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.CareerResponse;
import com.academic.enrollment.domain.model.Career;
import com.academic.enrollment.domain.model.Faculty;
import org.springframework.stereotype.Component;

@Component
public class CareerMapper {

    public CareerResponse toResponse(Career career, Faculty faculty, Integer totalCourses) {
        CareerResponse response = new CareerResponse();
        response.setId(career.getId().toString());
        response.setFacultyId(career.getFacultyId().toString());
        response.setFacultyName(faculty != null ? faculty.getName() : null);
        response.setName(career.getName());
        response.setDescription(career.getDescription());
        response.setDurationSemesters(career.getDurationSemesters());
        response.setDegreeAwarded(career.getDegreeAwarded());
        response.setTotalCourses(totalCourses != null ? totalCourses : 0);
        response.setRegistrationDate(career.getRegistrationDate());
        response.setActive(career.isActive());
        return response;
    }
}
