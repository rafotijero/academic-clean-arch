package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.GradeRecordResponse;
import com.academic.enrollment.domain.model.GradeRecord;
import com.academic.enrollment.domain.model.Enrollment;
import com.academic.enrollment.domain.model.Student;
import com.academic.enrollment.domain.model.Course;
import com.academic.enrollment.domain.model.Section;
import com.academic.enrollment.domain.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class GradeRecordMapper {

    public GradeRecordResponse toResponse(GradeRecord gradeRecord, Enrollment enrollment,
                                         Student student, Course course, Section section,
                                         Professor professor) {
        GradeRecordResponse response = new GradeRecordResponse();
        response.setId(gradeRecord.getId().toString());
        response.setEnrollmentId(enrollment.getId().toString());
        response.setStudentId(student.getId().toString());
        response.setStudentName(student.getFullName());
        response.setCourseId(course.getId().toString());
        response.setCourseName(course.getName());
        response.setCourseCode(course.getCode().getValue());
        response.setSectionCode(section.getCode());
        response.setProfessorName(professor.getFullName());
        response.setAcademicPeriod(section.getAcademicPeriod().getValue());
        response.setGrade(gradeRecord.getGrade() != null ? gradeRecord.getGrade().getValue() : null);
        response.setPassing(gradeRecord.isPassing());
        response.setObservation(gradeRecord.getObservation());
        response.setRegistrationDate(gradeRecord.getRegistrationDate());
        return response;
    }
}
