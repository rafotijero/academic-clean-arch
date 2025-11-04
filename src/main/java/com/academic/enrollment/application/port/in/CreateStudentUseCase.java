package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.CreateStudentCommand;
import com.academic.enrollment.application.dto.response.StudentResponse;

public interface CreateStudentUseCase {
    StudentResponse create(CreateStudentCommand command);
}
