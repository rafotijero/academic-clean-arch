package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.FacultyResponse;
import com.academic.enrollment.domain.model.Faculty;
import org.springframework.stereotype.Component;

@Component
public class FacultyMapper {

    public FacultyResponse toResponse(Faculty faculty, Integer totalCareers) {
        FacultyResponse response = new FacultyResponse();
        response.setId(faculty.getId().toString());
        response.setName(faculty.getName());
        response.setDescription(faculty.getDescription());
        response.setLocation(faculty.getLocation());
        response.setDean(faculty.getDean());
        response.setTotalCareers(totalCareers);
        response.setRegistrationDate(faculty.getRegistrationDate());
        response.setActive(faculty.isActive());
        return response;
    }
}
