package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.ProcessPaymentUseCase;
import com.academic.enrollment.application.port.out.PaymentGatewayPort;
import com.academic.enrollment.application.dto.command.CreatePaymentCommand;
import com.academic.enrollment.application.dto.response.PaymentResponse;
import com.academic.enrollment.application.mapper.PaymentMapper;
import com.academic.enrollment.domain.model.*;
import com.academic.enrollment.domain.model.valueobjects.*;
import com.academic.enrollment.domain.repository.*;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
public class PaymentApplicationService implements ProcessPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentMapper paymentMapper;

    public PaymentApplicationService(PaymentRepository paymentRepository,
                                    EnrollmentRepository enrollmentRepository,
                                    StudentRepository studentRepository,
                                    SectionRepository sectionRepository,
                                    CourseRepository courseRepository,
                                    PaymentGatewayPort paymentGatewayPort,
                                    PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.paymentGatewayPort = paymentGatewayPort;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentResponse process(CreatePaymentCommand command) {
        // Validar que la matrícula existe
        Enrollment enrollment = enrollmentRepository.findById(EnrollmentId.of(command.getEnrollmentId()))
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found: " + command.getEnrollmentId()));

        // Validar que la matrícula está pagada
        if (enrollment.getStatus() != EnrollmentStatus.PAGADO) {
            throw new IllegalArgumentException("Enrollment must be paid before processing payment");
        }

        // Procesar el pago a través del gateway externo
        PaymentGatewayPort.PaymentResult paymentResult = paymentGatewayPort.processPayment(
                command.getPaymentMethod(),
                command.getAmount(),
                command.getReference(),
                "Payment for enrollment: " + command.getEnrollmentId()
        );

        if (!paymentResult.isSuccess()) {
            throw new IllegalArgumentException("Payment processing failed: " + paymentResult.getMessage());
        }

        // Crear el registro de pago
        Payment payment = Payment.create(
                enrollment.getId(),
                new Money(command.getAmount()),
                command.getPaymentMethod(),
                command.getReference()
        );

        // Procesar el pago
        payment.process();

        // Guardar el pago
        Payment savedPayment = paymentRepository.save(payment);

        // Obtener información adicional para la respuesta y notificación
        Student student = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Section section = sectionRepository.findById(enrollment.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));

        Course course = courseRepository.findById(section.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // Convertir a DTO de respuesta
        return paymentMapper.toResponse(savedPayment, enrollment, student, course);
    }
}
