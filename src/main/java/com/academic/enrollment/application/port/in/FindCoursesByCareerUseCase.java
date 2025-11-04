package com.academic.enrollment.application.port.in;
import com.academic.enrollment.application.dto.query.FindCoursesByCareerQuery;
import com.academic.enrollment.application.dto.response.CourseResponse;
import java.util.List;
public interface FindCoursesByCareerUseCase {
    List<CourseResponse> findByCareerId(FindCoursesByCareerQuery query);
}
