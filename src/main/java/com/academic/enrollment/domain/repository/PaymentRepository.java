package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Payment;
import com.academic.enrollment.domain.model.valueobjects.PaymentId;
import com.academic.enrollment.domain.model.valueobjects.EnrollmentId;
import com.academic.enrollment.domain.model.valueobjects.PaymentStatus;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> findById(PaymentId id);

    List<Payment> findAll();

    List<Payment> findByEnrollmentId(EnrollmentId enrollmentId);

    List<Payment> findByStatus(PaymentStatus status);

    Optional<Payment> findByReference(String reference);

    void delete(PaymentId id);
}
