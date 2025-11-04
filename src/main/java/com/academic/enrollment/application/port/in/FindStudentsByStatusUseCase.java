package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.query.FindStudentsByStatusQuery;
import com.academic.enrollment.application.dto.response.StudentResponse;
import java.util.List;

public interface FindStudentsByStatusUseCase {
    List<StudentResponse> findByStatus(FindStudentsByStatusQuery query);
}
