package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.RecordGradeCommand;
import com.academic.enrollment.application.dto.response.GradeRecordResponse;
import com.academic.enrollment.application.port.in.RecordGradeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/grades")
@Tag(name = "Grade Record", description = "Grade record management endpoints")
public class GradeRecordController {

    private final RecordGradeUseCase recordGradeUseCase;

    public GradeRecordController(RecordGradeUseCase recordGradeUseCase) {
        this.recordGradeUseCase = recordGradeUseCase;
    }

    @PostMapping
    @Operation(summary = "Record a grade for an enrollment")
    public ResponseEntity<GradeRecordResponse> recordGrade(
            @Valid @RequestBody RecordGradeCommand command) {
        GradeRecordResponse response = recordGradeUseCase.record(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
