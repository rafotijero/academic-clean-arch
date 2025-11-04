package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.UpdateCareerCommand;
import com.academic.enrollment.application.dto.response.CareerResponse;


public interface UpdateCareerUseCase {
    CareerResponse updateCareer(Long id, UpdateCareerCommand command);
}
