package com.academic.enrollment.application.port.in;
import com.academic.enrollment.application.dto.response.FacultyResponse;
import java.util.List;
public interface FindActiveFacultiesUseCase {
    List<FacultyResponse> findActive();
}
