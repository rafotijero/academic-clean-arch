package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.UpdateStudentContactCommand;
import com.academic.enrollment.application.dto.response.StudentResponse;

public interface UpdateStudentContactUseCase {
    StudentResponse updateContact(UpdateStudentContactCommand command);
}
