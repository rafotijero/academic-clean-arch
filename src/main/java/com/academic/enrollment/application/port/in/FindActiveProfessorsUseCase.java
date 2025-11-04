package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.response.ProfessorResponse;
import java.util.List;

public interface FindActiveProfessorsUseCase {
    List<ProfessorResponse> findActive();
}
