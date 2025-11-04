package com.academic.enrollment.application.port.in;
import com.academic.enrollment.application.dto.command.CreateCourseCommand;
import com.academic.enrollment.application.dto.response.CourseResponse;
public interface CreateCourseUseCase {
    CourseResponse create(CreateCourseCommand command);
}
