package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.RecordGradeCommand;
import com.academic.enrollment.application.dto.response.GradeRecordResponse;

public interface RecordGradeUseCase {
    GradeRecordResponse record(RecordGradeCommand command);
}
