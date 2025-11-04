package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.CreateEnrollmentUseCase;
import com.academic.enrollment.application.port.in.MarkEnrollmentAsPaidUseCase;
import com.academic.enrollment.application.port.in.FindEnrollmentsByStudentUseCase;
import com.academic.enrollment.application.dto.command.CreateEnrollmentCommand;
import com.academic.enrollment.application.dto.command.MarkEnrollmentAsPaidCommand;
import com.academic.enrollment.application.dto.query.FindEnrollmentsByStudentQuery;
import com.academic.enrollment.application.dto.response.EnrollmentResponse;
import com.academic.enrollment.application.mapper.EnrollmentMapper;
import com.academic.enrollment.domain.model.*;
import com.academic.enrollment.domain.model.valueobjects.*;
import com.academic.enrollment.domain.repository.*;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class EnrollmentApplicationService implements CreateEnrollmentUseCase,
                                                     MarkEnrollmentAsPaidUseCase,
                                                     FindEnrollmentsByStudentUseCase {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentApplicationService(EnrollmentRepository enrollmentRepository,
                                       StudentRepository studentRepository,
                                       SectionRepository sectionRepository,
                                       CourseRepository courseRepository,
                                       ProfessorRepository professorRepository,
                                       EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    public EnrollmentResponse create(CreateEnrollmentCommand command) {
        // Validar que el estudiante existe
        Student student = studentRepository.findById(StudentId.of(command.getStudentId()))
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + command.getStudentId()));

        // Validar que el estudiante está activo
        if (!student.isActive()) {
            throw new IllegalArgumentException("Student is not active: " + command.getStudentId());
        }

        // Validar que la sección existe
        Section section = sectionRepository.findById(SectionId.of(command.getSectionId()))
                .orElseThrow(() -> new IllegalArgumentException("Section not found: " + command.getSectionId()));

        // Validar que la sección está activa
        if (!section.isActive()) {
            throw new IllegalArgumentException("Section is not active: " + command.getSectionId());
        }

        // Validar que el estudiante no está ya matriculado en esta sección
        if (enrollmentRepository.existsByStudentAndSection(student.getId(), section.getId())) {
            throw new IllegalArgumentException("Student is already enrolled in this section");
        }

        // Contar matrículas actuales en la sección
        int currentEnrollment = enrollmentRepository.countBySectionId(section.getId());

        // Validar capacidad de la sección
        if (currentEnrollment >= section.getMaxCapacity()) {
            throw new IllegalArgumentException("Section is full. Max capacity: " + section.getMaxCapacity());
        }

        // Crear la matrícula
        Enrollment enrollment = Enrollment.create(
                student.getId(),
                section.getId(),
                new Money(command.getCost())
        );

        // Guardar la matrícula
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        // Obtener información adicional para la respuesta y notificación
        Course course = courseRepository.findById(section.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Professor professor = professorRepository.findById(section.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor not found"));

        // Convertir a DTO de respuesta
        return enrollmentMapper.toResponse(savedEnrollment, student, section, course, professor);
    }

    @Override
    public EnrollmentResponse markAsPaid(MarkEnrollmentAsPaidCommand command) {
        // Buscar la matrícula
        Enrollment enrollment = enrollmentRepository.findById(EnrollmentId.of(command.getEnrollmentId()))
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found: " + command.getEnrollmentId()));

        // Marcar como pagada
        enrollment.markAsPaid(command.getPaymentMethod());

        // Guardar cambios
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        // Obtener información adicional para la respuesta
        Student student = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Section section = sectionRepository.findById(enrollment.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));

        Course course = courseRepository.findById(section.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Professor professor = professorRepository.findById(section.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor not found"));

        // Convertir a DTO de respuesta
        return enrollmentMapper.toResponse(updatedEnrollment, student, section, course, professor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> findByStudent(FindEnrollmentsByStudentQuery query) {
        StudentId studentId = StudentId.of(query.getStudentId());

        // Validar que el estudiante existe
        if (studentRepository.findById(studentId).isEmpty()) {
            throw new IllegalArgumentException("Student not found: " + query.getStudentId());
        }

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        return enrollments.stream()
                .map(this::enrichEnrollmentResponse)
                .collect(Collectors.toList());
    }

    private EnrollmentResponse enrichEnrollmentResponse(Enrollment enrollment) {
        Student student = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Section section = sectionRepository.findById(enrollment.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));

        Course course = courseRepository.findById(section.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Professor professor = professorRepository.findById(section.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor not found"));

        return enrollmentMapper.toResponse(enrollment, student, section, course, professor);
    }
}
