package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.RecordGradeUseCase;
import com.academic.enrollment.application.dto.command.RecordGradeCommand;
import com.academic.enrollment.application.dto.response.GradeRecordResponse;
import com.academic.enrollment.application.mapper.GradeRecordMapper;
import com.academic.enrollment.domain.model.*;
import com.academic.enrollment.domain.model.valueobjects.*;
import com.academic.enrollment.domain.repository.*;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
public class GradeApplicationService implements RecordGradeUseCase {

    private final GradeRecordRepository gradeRecordRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final GradeRecordMapper gradeRecordMapper;

    public GradeApplicationService(GradeRecordRepository gradeRecordRepository,
                                  EnrollmentRepository enrollmentRepository,
                                  StudentRepository studentRepository,
                                  SectionRepository sectionRepository,
                                  CourseRepository courseRepository,
                                  ProfessorRepository professorRepository,
                                  GradeRecordMapper gradeRecordMapper) {
        this.gradeRecordRepository = gradeRecordRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.gradeRecordMapper = gradeRecordMapper;
    }

    @Override
    public GradeRecordResponse record(RecordGradeCommand command) {
        // Validar que la matrícula existe
        Enrollment enrollment = enrollmentRepository.findById(EnrollmentId.of(command.getEnrollmentId()))
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found: " + command.getEnrollmentId()));

        // Validar que la matrícula está completada
        if (enrollment.getStatus() != EnrollmentStatus.COMPLETADO) {
            throw new IllegalArgumentException("Cannot record grade for enrollment that is not completed");
        }

        // Validar si ya existe una calificación para esta matrícula
        if (gradeRecordRepository.existsByEnrollmentId(enrollment.getId())) {
            throw new IllegalArgumentException("Grade already exists for this enrollment");
        }

        // Crear la calificación
        Grade grade = new Grade(command.getGrade());
        GradeRecord gradeRecord = GradeRecord.create(
                enrollment.getId(),
                grade,
                command.getObservation()
        );

        // Guardar la calificación
        GradeRecord savedGradeRecord = gradeRecordRepository.save(gradeRecord);

        // Obtener información adicional para la respuesta y notificación
        Student student = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Section section = sectionRepository.findById(enrollment.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));

        Course course = courseRepository.findById(section.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Professor professor = professorRepository.findById(section.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor not found"));

        // Convertir a DTO de respuesta
        return gradeRecordMapper.toResponse(savedGradeRecord, enrollment, student, course, section, professor);
    }
}
