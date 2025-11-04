package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.CreateStudentCommand;
import com.academic.enrollment.application.dto.command.UpdateStudentContactCommand;
import com.academic.enrollment.application.dto.query.FindStudentsByStatusQuery;
import com.academic.enrollment.application.dto.response.StudentResponse;
import com.academic.enrollment.application.port.in.CreateStudentUseCase;
import com.academic.enrollment.application.port.in.FindStudentsByStatusUseCase;
import com.academic.enrollment.application.port.in.UpdateStudentContactUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Student", description = "Student management endpoints")
public class StudentController {

    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentContactUseCase updateStudentContactUseCase;
    private final FindStudentsByStatusUseCase findStudentsByStatusUseCase;

    public StudentController(CreateStudentUseCase createStudentUseCase,
                              UpdateStudentContactUseCase updateStudentContactUseCase,
                              FindStudentsByStatusUseCase findStudentsByStatusUseCase) {
        this.createStudentUseCase = createStudentUseCase;
        this.updateStudentContactUseCase = updateStudentContactUseCase;
        this.findStudentsByStatusUseCase = findStudentsByStatusUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody CreateStudentCommand command) {
        StudentResponse response = createStudentUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/contact")
    @Operation(summary = "Update student contact information")
    public ResponseEntity<StudentResponse> updateContact(
            @PathVariable String id,
            @Valid @RequestBody UpdateStudentContactCommand command) {
        StudentResponse response = updateStudentContactUseCase.updateContact(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Find students by status")
    public ResponseEntity<List<StudentResponse>> findByStatus(
            @RequestParam(defaultValue = "true") boolean active) {
        FindStudentsByStatusQuery query = new FindStudentsByStatusQuery(active);
        List<StudentResponse> response = findStudentsByStatusUseCase.findByStatus(query);
        return ResponseEntity.ok(response);
    }
}
