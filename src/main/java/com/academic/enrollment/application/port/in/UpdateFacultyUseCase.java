package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.UpdateFacultyCommand;
import com.academic.enrollment.application.dto.response.FacultyResponse;


public interface UpdateFacultyUseCase {
    FacultyResponse updateFaculty(Long id, UpdateFacultyCommand command);
}
