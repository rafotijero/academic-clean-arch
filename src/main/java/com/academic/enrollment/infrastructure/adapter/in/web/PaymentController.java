package com.academic.enrollment.infrastructure.adapter.in.web;

import com.academic.enrollment.application.dto.command.CreatePaymentCommand;
import com.academic.enrollment.application.dto.response.PaymentResponse;
import com.academic.enrollment.application.port.in.ProcessPaymentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payment", description = "Payment management endpoints")
public class PaymentController {

    private final ProcessPaymentUseCase processPaymentUseCase;

    public PaymentController(ProcessPaymentUseCase processPaymentUseCase) {
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @PostMapping
    @Operation(summary = "Process a new payment")
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody CreatePaymentCommand command) {
        PaymentResponse response = processPaymentUseCase.process(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
