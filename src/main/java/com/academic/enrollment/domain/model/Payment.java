package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.*;
import java.time.LocalDate;
import java.util.Objects;

public class Payment {
    private final PaymentId id;
    private final EnrollmentId enrollmentId;
    private LocalDate paymentDate;
    private Money amount;
    private String paymentMethod;
    private String reference;
    private PaymentStatus status;
    private LocalDate registrationDate;

    public Payment(PaymentId id, EnrollmentId enrollmentId, LocalDate paymentDate,
                   Money amount, String paymentMethod, String reference,
                   PaymentStatus status, LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "Payment ID cannot be null");
        this.enrollmentId = Objects.requireNonNull(enrollmentId, "Enrollment ID cannot be null");
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.setPaymentMethod(paymentMethod);
        this.paymentDate = paymentDate != null ? paymentDate : LocalDate.now();
        this.reference = reference;
        this.status = status != null ? status : PaymentStatus.PENDIENTE;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
    }

    public static Payment create(EnrollmentId enrollmentId, Money amount, String paymentMethod, String reference) {
        return new Payment(null, enrollmentId, LocalDate.now(),
                amount, paymentMethod, reference, PaymentStatus.PENDIENTE, LocalDate.now());
    }

    public void process() {
        if (status == PaymentStatus.PROCESADO) {
            throw new IllegalStateException("Payment is already processed");
        }
        this.status = PaymentStatus.PROCESADO;
    }

    public void reject() {
        if (status == PaymentStatus.PROCESADO) {
            throw new IllegalStateException("Cannot reject a processed payment");
        }
        this.status = PaymentStatus.RECHAZADO;
    }

    private void setPaymentMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        if (paymentMethod.length() > 50) {
            throw new IllegalArgumentException("Payment method cannot exceed 50 characters");
        }
        this.paymentMethod = paymentMethod.trim();
    }

    // Getters
    public PaymentId getId() { return id; }
    public EnrollmentId getEnrollmentId() { return enrollmentId; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public Money getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getReference() { return reference; }
    public PaymentStatus getStatus() { return status; }
    public LocalDate getRegistrationDate() { return registrationDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
