package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.query.FindEnrollmentsByStudentQuery;
import com.academic.enrollment.application.dto.response.EnrollmentResponse;
import java.util.List;

public interface FindEnrollmentsByStudentUseCase {
    List<EnrollmentResponse> findByStudent(FindEnrollmentsByStudentQuery query);
}
