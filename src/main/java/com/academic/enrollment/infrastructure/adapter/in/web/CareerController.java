package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.CreateCareerCommand;
import com.academic.enrollment.application.dto.command.UpdateCareerCommand;
import com.academic.enrollment.application.dto.query.FindCareersByFacultyQuery;
import com.academic.enrollment.application.dto.response.CareerResponse;
import com.academic.enrollment.application.port.in.CreateCareerUseCase;
import com.academic.enrollment.application.port.in.FindCareersByFacultyUseCase;
import com.academic.enrollment.application.port.in.DeleteCareerUseCase;
import com.academic.enrollment.application.port.in.UpdateCareerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/careers")
@Tag(name = "Career", description = "Career management endpoints")
public class CareerController {

    private final CreateCareerUseCase createCareerUseCase;
    private final FindCareersByFacultyUseCase findCareersByFacultyUseCase;
    private final UpdateCareerUseCase updateCareerUseCase;
    private final DeleteCareerUseCase deleteCareerUseCase;

    public CareerController(CreateCareerUseCase createCareerUseCase,
                             FindCareersByFacultyUseCase findCareersByFacultyUseCase,
                             UpdateCareerUseCase updateCareerUseCase,
                             DeleteCareerUseCase deleteCareerUseCase) {
        this.createCareerUseCase = createCareerUseCase;
        this.findCareersByFacultyUseCase = findCareersByFacultyUseCase;
        this.updateCareerUseCase = updateCareerUseCase;
        this.deleteCareerUseCase = deleteCareerUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new career")
    public ResponseEntity<CareerResponse> createCareer(@Valid @RequestBody CreateCareerCommand command) {
        CareerResponse response = createCareerUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Find careers by faculty")
    public ResponseEntity<List<CareerResponse>> findByFacultyId(@RequestParam String facultyId) {
        FindCareersByFacultyQuery query = new FindCareersByFacultyQuery(facultyId);
        List<CareerResponse> response = findCareersByFacultyUseCase.findByFacultyId(query);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing career")
    public ResponseEntity<CareerResponse> updateCareer(@PathVariable Long id, @Valid @RequestBody UpdateCareerCommand command) {
        CareerResponse response = updateCareerUseCase.updateCareer(id, command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deactivate a career by ID")
    public ResponseEntity<Void> deleteCareer(@PathVariable Long id) {
        deleteCareerUseCase.deleteCareer(id);
        return ResponseEntity.noContent().build();
    }
}
