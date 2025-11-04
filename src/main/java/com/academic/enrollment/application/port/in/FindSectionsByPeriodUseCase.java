package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.query.FindSectionsByPeriodQuery;
import com.academic.enrollment.application.dto.response.SectionResponse;
import java.util.List;

public interface FindSectionsByPeriodUseCase {
    List<SectionResponse> findByPeriod(FindSectionsByPeriodQuery query);
}
