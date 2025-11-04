package com.academic.enrollment.application.port.in;

import com.academic.enrollment.application.dto.command.CreatePaymentCommand;
import com.academic.enrollment.application.dto.response.PaymentResponse;

public interface ProcessPaymentUseCase {
    PaymentResponse process(CreatePaymentCommand command);
}
