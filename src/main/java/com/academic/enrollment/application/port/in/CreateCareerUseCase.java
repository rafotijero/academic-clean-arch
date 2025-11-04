package com.academic.enrollment.application.port.in;
import com.academic.enrollment.application.dto.command.CreateCareerCommand;
import com.academic.enrollment.application.dto.response.CareerResponse;
public interface CreateCareerUseCase {
    CareerResponse create(CreateCareerCommand command);
}
