package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.CreateProfessorCommand;
import com.academic.enrollment.application.dto.query.FindProfessorsBySpecialtyQuery;
import com.academic.enrollment.application.dto.response.ProfessorResponse;
import com.academic.enrollment.application.port.in.CreateProfessorUseCase;
import com.academic.enrollment.application.port.in.FindProfessorsBySpecialtyUseCase;
import com.academic.enrollment.application.port.in.FindActiveProfessorsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professors")
@Tag(name = "Professor", description = "Professor management endpoints")
public class ProfessorController {

    private final CreateProfessorUseCase createProfessorUseCase;
    private final FindProfessorsBySpecialtyUseCase findProfessorsBySpecialtyUseCase;
    private final FindActiveProfessorsUseCase findActiveProfessorsUseCase;

    public ProfessorController(CreateProfessorUseCase createProfessorUseCase,
                                FindProfessorsBySpecialtyUseCase findProfessorsBySpecialtyUseCase,
                                FindActiveProfessorsUseCase findActiveProfessorsUseCase) {
        this.createProfessorUseCase = createProfessorUseCase;
        this.findProfessorsBySpecialtyUseCase = findProfessorsBySpecialtyUseCase;
        this.findActiveProfessorsUseCase = findActiveProfessorsUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new professor")
    public ResponseEntity<ProfessorResponse> createProfessor(@Valid @RequestBody CreateProfessorCommand command) {
        ProfessorResponse response = createProfessorUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/specialty")
    @Operation(summary = "Find professors by specialty")
    public ResponseEntity<List<ProfessorResponse>> findBySpecialty(@RequestParam String specialty) {
        FindProfessorsBySpecialtyQuery query = new FindProfessorsBySpecialtyQuery(specialty);
        List<ProfessorResponse> response = findProfessorsBySpecialtyUseCase.findBySpecialty(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Find professors by active status")
    public ResponseEntity<List<ProfessorResponse>> findByActiveStatus(
            @RequestParam(defaultValue = "true") boolean active) {
        if (active) {
            List<ProfessorResponse> response = findActiveProfessorsUseCase.findActive();
            return ResponseEntity.ok(response);
        }
        // Si se necesita listar inactivos, agregar lógica aquí
        List<ProfessorResponse> response = findActiveProfessorsUseCase.findActive();
        return ResponseEntity.ok(response);
    }
}
