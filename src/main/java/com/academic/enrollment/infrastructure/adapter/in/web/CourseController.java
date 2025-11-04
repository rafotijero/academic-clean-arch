package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.CreateCourseCommand;
import com.academic.enrollment.application.dto.query.FindCoursesByCareerQuery;
import com.academic.enrollment.application.dto.response.CourseResponse;
import com.academic.enrollment.application.port.in.CreateCourseUseCase;
import com.academic.enrollment.application.port.in.FindCoursesByCareerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Course", description = "Course management endpoints")
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;
    private final FindCoursesByCareerUseCase findCoursesByCareerUseCase;

    public CourseController(CreateCourseUseCase createCourseUseCase,
                             FindCoursesByCareerUseCase findCoursesByCareerUseCase) {
        this.createCourseUseCase = createCourseUseCase;
        this.findCoursesByCareerUseCase = findCoursesByCareerUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new course")
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CreateCourseCommand command) {
        CourseResponse response = createCourseUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Find courses by career")
    public ResponseEntity<List<CourseResponse>> findByCareerId(@RequestParam String careerId) {
        FindCoursesByCareerQuery query = new FindCoursesByCareerQuery(careerId);
        List<CourseResponse> response = findCoursesByCareerUseCase.findByCareerId(query);
        return ResponseEntity.ok(response);
    }
}
