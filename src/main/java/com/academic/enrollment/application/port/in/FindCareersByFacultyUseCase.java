package com.academic.enrollment.application.port.in;
import com.academic.enrollment.application.dto.query.FindCareersByFacultyQuery;
import com.academic.enrollment.application.dto.response.CareerResponse;
import java.util.List;
public interface FindCareersByFacultyUseCase {
    List<CareerResponse> findByFacultyId(FindCareersByFacultyQuery query);
}
