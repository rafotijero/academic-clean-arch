package com.academic.enrollment.application.port.in;
import com.academic.enrollment.application.dto.command.CreateFacultyCommand;
import com.academic.enrollment.application.dto.response.FacultyResponse;
public interface CreateFacultyUseCase {
    FacultyResponse create(CreateFacultyCommand command);
}
