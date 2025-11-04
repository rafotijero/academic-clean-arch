package com.academic.enrollment.domain.model.event;

import com.academic.enrollment.domain.model.valueobjects.PaymentId;
import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.Money;

import java.time.LocalDateTime;

public class PaymentProcessedEvent implements DomainEvent {
    private final Long eventId;
    private final PaymentId paymentId;
    private final EnrollmentId enrollmentId;
    private final Money amount;
    private final String paymentMethod;
    private final LocalDateTime occurredOn;

    public PaymentProcessedEvent(PaymentId paymentId, EnrollmentId enrollmentId,
                                 Money amount, String paymentMethod) {
        this.eventId = System.currentTimeMillis();
        this.paymentId = paymentId;
        this.enrollmentId = enrollmentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public Long getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getEventType() {
        return "PaymentProcessed";
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    public EnrollmentId getEnrollmentId() {
        return enrollmentId;
    }

    public Money getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
