package com.academic.enrollment.domain.model.valueobjects;

import java.util.Objects;

public class PaymentId {
    private final Long value;

    public PaymentId(Long value) {
        this.value = Objects.requireNonNull(value, "Payment ID cannot be null");
    }

    public static PaymentId of(Long value) {
        return new PaymentId(value);
    }

    public static PaymentId of(String value) {
        return new PaymentId(Long.parseLong(value));
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentId)) return false;
        PaymentId paymentId = (PaymentId) o;
        return Objects.equals(value, paymentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
