package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.SectionResponse;
import com.academic.enrollment.domain.model.Section;
import com.academic.enrollment.domain.model.Course;
import com.academic.enrollment.domain.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {

    public SectionResponse toResponse(Section section, Course course,
                                     Professor professor, Integer currentEnrollment) {
        SectionResponse response = new SectionResponse();
        response.setId(section.getId().toString());
        response.setCourseId(course.getId().toString());
        response.setCourseName(course.getName());
        response.setCourseCode(course.getCode().getValue());
        response.setProfessorId(professor.getId().toString());
        response.setProfessorName(professor.getFullName());
        response.setCode(section.getCode());
        response.setMaxCapacity(section.getMaxCapacity());
        response.setCurrentEnrollment(currentEnrollment);
        response.setAvailableSeats(section.getMaxCapacity() - currentEnrollment);
        response.setClassroom(section.getClassroom());
        response.setSchedule(section.getSchedule());
        response.setDays(section.getDays());
        response.setAcademicPeriod(section.getAcademicPeriod().getValue());
        response.setStartDate(section.getStartDate());
        response.setEndDate(section.getEndDate());
        response.setRegistrationDate(section.getRegistrationDate());
        response.setActive(section.isActive());
        return response;
    }
}
