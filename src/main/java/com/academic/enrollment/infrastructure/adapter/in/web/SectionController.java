package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.query.FindSectionsByPeriodQuery;
import com.academic.enrollment.application.dto.response.SectionResponse;
import com.academic.enrollment.application.port.in.FindSectionsByPeriodUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sections")
@Tag(name = "Section", description = "Section management endpoints")
public class SectionController {

    private final FindSectionsByPeriodUseCase findSectionsByPeriodUseCase;

    public SectionController(FindSectionsByPeriodUseCase findSectionsByPeriodUseCase) {
        this.findSectionsByPeriodUseCase = findSectionsByPeriodUseCase;
    }

    @GetMapping
    @Operation(summary = "Find sections by academic period")
    public ResponseEntity<List<SectionResponse>> findByPeriod(
            @RequestParam String period) {
        FindSectionsByPeriodQuery query = new FindSectionsByPeriodQuery(period, null, null, null);
        List<SectionResponse> response = findSectionsByPeriodUseCase.findByPeriod(query);
        return ResponseEntity.ok(response);
    }
}
