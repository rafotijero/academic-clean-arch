package com.academic.enrollment.application.mapper;

import com.academic.enrollment.application.dto.response.PaymentResponse;
import com.academic.enrollment.domain.model.Payment;
import com.academic.enrollment.domain.model.Enrollment;
import com.academic.enrollment.domain.model.Student;
import com.academic.enrollment.domain.model.Course;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment, Enrollment enrollment,
                                     Student student, Course course) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId().toString());
        response.setEnrollmentId(enrollment.getId().toString());
        response.setStudentName(student.getFullName());
        response.setCourseName(course.getName());
        response.setPaymentDate(payment.getPaymentDate());
        response.setAmount(payment.getAmount().getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setReference(payment.getReference());
        response.setStatus(payment.getStatus().name());
        response.setRegistrationDate(payment.getRegistrationDate());
        return response;
    }
}
