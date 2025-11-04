package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.EnrollmentResponse;
import com.academic.enrollment.domain.model.Enrollment;
import com.academic.enrollment.domain.model.Student;
import com.academic.enrollment.domain.model.Section;
import com.academic.enrollment.domain.model.Course;
import com.academic.enrollment.domain.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public EnrollmentResponse toResponse(Enrollment enrollment, Student student,
                                        Section section, Course course, Professor professor) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(enrollment.getId().toString());
        response.setStudentId(student.getId().toString());
        response.setStudentName(student.getFullName());
        response.setStudentDni(student.getDni().getValue());
        response.setSectionId(section.getId().toString());
        response.setSectionCode(section.getCode());
        response.setCourseId(course.getId().toString());
        response.setCourseName(course.getName());
        response.setCourseCode(course.getCode().getValue());
        response.setProfessorName(professor.getFullName());
        response.setAcademicPeriod(section.getAcademicPeriod().getValue());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setStatus(enrollment.getStatus().name());
        response.setCost(enrollment.getCost().getAmount());
        response.setPaymentMethod(enrollment.getPaymentMethod());
        response.setRegistrationDate(enrollment.getRegistrationDate());
        return response;
    }
}
