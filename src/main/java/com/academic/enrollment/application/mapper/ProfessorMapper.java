package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.ProfessorResponse;
import com.academic.enrollment.domain.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public ProfessorResponse toResponse(Professor professor) {
        ProfessorResponse response = new ProfessorResponse();
        response.setId(professor.getId().toString());
        response.setFirstName(professor.getFirstName());
        response.setLastName(professor.getLastName());
        response.setFullName(professor.getFullName());
        response.setDni(professor.getDni().getValue());
        response.setEmail(professor.getEmail().getValue());
        response.setPhone(professor.getPhone());
        response.setSpecialty(professor.getSpecialty());
        response.setAcademicDegree(professor.getAcademicDegree());
        response.setRegistrationDate(professor.getRegistrationDate());
        response.setActive(professor.isActive());
        return response;
    }
}
