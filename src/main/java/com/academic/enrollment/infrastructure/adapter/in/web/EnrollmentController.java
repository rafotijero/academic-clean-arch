package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.CreateEnrollmentCommand;
import com.academic.enrollment.application.dto.command.MarkEnrollmentAsPaidCommand;
import com.academic.enrollment.application.dto.query.FindEnrollmentsByStudentQuery;
import com.academic.enrollment.application.dto.response.EnrollmentResponse;
import com.academic.enrollment.application.port.in.CreateEnrollmentUseCase;
import com.academic.enrollment.application.port.in.FindEnrollmentsByStudentUseCase;
import com.academic.enrollment.application.port.in.MarkEnrollmentAsPaidUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@Tag(name = "Enrollment", description = "Enrollment management endpoints")
public class EnrollmentController {

    private final CreateEnrollmentUseCase createEnrollmentUseCase;
    private final MarkEnrollmentAsPaidUseCase markEnrollmentAsPaidUseCase;
    private final FindEnrollmentsByStudentUseCase findEnrollmentsByStudentUseCase;

    public EnrollmentController(CreateEnrollmentUseCase createEnrollmentUseCase,
                                 MarkEnrollmentAsPaidUseCase markEnrollmentAsPaidUseCase,
                                 FindEnrollmentsByStudentUseCase findEnrollmentsByStudentUseCase) {
        this.createEnrollmentUseCase = createEnrollmentUseCase;
        this.markEnrollmentAsPaidUseCase = markEnrollmentAsPaidUseCase;
        this.findEnrollmentsByStudentUseCase = findEnrollmentsByStudentUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new enrollment")
    public ResponseEntity<EnrollmentResponse> createEnrollment(
            @Valid @RequestBody CreateEnrollmentCommand command) {
        EnrollmentResponse response = createEnrollmentUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/mark-paid")
    @Operation(summary = "Mark enrollment as paid")
    public ResponseEntity<EnrollmentResponse> markAsPaid(
            @PathVariable String id,
            @Valid @RequestBody MarkEnrollmentAsPaidCommand command) {
        EnrollmentResponse response = markEnrollmentAsPaidUseCase.markAsPaid(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Find enrollments by student")
    public ResponseEntity<List<EnrollmentResponse>> findByStudent(@PathVariable String studentId) {
        FindEnrollmentsByStudentQuery query = new FindEnrollmentsByStudentQuery(studentId, null, null);
        List<EnrollmentResponse> response = findEnrollmentsByStudentUseCase.findByStudent(query);
        return ResponseEntity.ok(response);
    }
}
