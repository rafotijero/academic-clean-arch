package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.CreateFacultyCommand;
import com.academic.enrollment.application.dto.command.UpdateFacultyCommand;
import com.academic.enrollment.application.dto.response.FacultyResponse;
import com.academic.enrollment.application.port.in.CreateFacultyUseCase;
import com.academic.enrollment.application.port.in.FindActiveFacultiesUseCase;
import com.academic.enrollment.application.port.in.DeleteFacultyUseCase;
import com.academic.enrollment.application.port.in.UpdateFacultyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/faculties")
@Tag(name = "Faculty", description = "Faculty management endpoints")
public class FacultyController {

    private final CreateFacultyUseCase createFacultyUseCase;
    private final FindActiveFacultiesUseCase findActiveFacultiesUseCase;
    private final UpdateFacultyUseCase updateFacultyUseCase;
    private final DeleteFacultyUseCase deleteFacultyUseCase;

    public FacultyController(CreateFacultyUseCase createFacultyUseCase,
                              FindActiveFacultiesUseCase findActiveFacultiesUseCase,
                              UpdateFacultyUseCase updateFacultyUseCase,
                              DeleteFacultyUseCase deleteFacultyUseCase) {
        this.createFacultyUseCase = createFacultyUseCase;
        this.findActiveFacultiesUseCase = findActiveFacultiesUseCase;
        this.updateFacultyUseCase = updateFacultyUseCase;
        this.deleteFacultyUseCase = deleteFacultyUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new faculty")
    public ResponseEntity<FacultyResponse> createFaculty(@Valid @RequestBody CreateFacultyCommand command) {
        FacultyResponse response = createFacultyUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Find faculties by active status")
    public ResponseEntity<List<FacultyResponse>> findByActiveStatus(
            @RequestParam(defaultValue = "true") boolean active) {
        if (active) {
            List<FacultyResponse> response = findActiveFacultiesUseCase.findActive();
            return ResponseEntity.ok(response);
        }
        // Si se necesita listar inactivos, agregar lógica aquí
        List<FacultyResponse> response = findActiveFacultiesUseCase.findActive();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing faculty")
    public ResponseEntity<FacultyResponse> updateFaculty(@PathVariable Long id, @Valid @RequestBody UpdateFacultyCommand command) {
        FacultyResponse response = updateFacultyUseCase.updateFaculty(id, command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deactivate a faculty by ID")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        deleteFacultyUseCase.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
