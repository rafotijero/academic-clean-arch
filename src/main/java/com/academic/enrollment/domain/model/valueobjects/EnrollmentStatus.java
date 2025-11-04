package com.academic.enrollment.domain.model.valueobjects;

public enum EnrollmentStatus {
    PENDIENTE,
    PAGADO,
    ANULADO,
    COMPLETADO;

    public boolean canBeModified() {
        return this == PENDIENTE || this == PAGADO;
    }

    public boolean canBeCancelled() {
        return this == PENDIENTE || this == PAGADO;
    }

    public boolean isActive() {
        return this == PAGADO || this == COMPLETADO;
    }

    public boolean canReceiveGrade() {
        return this == PAGADO || this == COMPLETADO;
    }
}
