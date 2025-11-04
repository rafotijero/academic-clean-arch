package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.CreateProfessorCommand;
import com.academic.enrollment.application.dto.response.ProfessorResponse;

public interface CreateProfessorUseCase {
    ProfessorResponse create(CreateProfessorCommand command);
}
