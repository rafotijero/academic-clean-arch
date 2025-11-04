package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.StudentResponse;
import com.academic.enrollment.domain.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId().toString());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setFullName(student.getFullName());
        response.setDni(student.getDni().getValue());
        response.setEmail(student.getEmail().getValue());
        response.setPhone(student.getPhone());
        response.setBirthDate(student.getBirthDate());
        response.setAge(student.getAge());
        response.setAddress(student.getAddress());
        response.setRegistrationDate(student.getRegistrationDate());
        response.setActive(student.isActive());
        return response;
    }
}
