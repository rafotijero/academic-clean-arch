package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.MarkEnrollmentAsPaidCommand;
import com.academic.enrollment.application.dto.response.EnrollmentResponse;

public interface MarkEnrollmentAsPaidUseCase {
    EnrollmentResponse markAsPaid(MarkEnrollmentAsPaidCommand command);
}
