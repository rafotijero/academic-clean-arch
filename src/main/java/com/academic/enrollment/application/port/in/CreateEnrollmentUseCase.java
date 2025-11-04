package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.CreateEnrollmentCommand;
import com.academic.enrollment.application.dto.response.EnrollmentResponse;

public interface CreateEnrollmentUseCase {
    EnrollmentResponse create(CreateEnrollmentCommand command);
}
