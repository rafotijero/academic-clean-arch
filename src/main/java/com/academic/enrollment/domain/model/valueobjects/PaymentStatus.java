package com.academic.enrollment.domain.model.valueobjects;

public enum PaymentStatus {
    PENDIENTE,
    PROCESADO,
    RECHAZADO;

    public boolean isSuccessful() {
        return this == PROCESADO;
    }

    public boolean canBeRetried() {
        return this == PENDIENTE || this == RECHAZADO;
    }
}
