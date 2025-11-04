package com.academic.enrollment.application.service;

import com.academic.enrollment.application.port.in.CreateStudentUseCase;
import com.academic.enrollment.application.port.in.UpdateStudentContactUseCase;
import com.academic.enrollment.application.port.in.FindStudentsByStatusUseCase;
import com.academic.enrollment.application.dto.command.CreateStudentCommand;
import com.academic.enrollment.application.dto.command.UpdateStudentContactCommand;
import com.academic.enrollment.application.dto.query.FindStudentsByStatusQuery;
import com.academic.enrollment.application.dto.response.StudentResponse;
import com.academic.enrollment.application.mapper.StudentMapper;
import com.academic.enrollment.domain.model.Student;
import com.academic.enrollment.domain.model.valueobjects.DNI;
import com.academic.enrollment.domain.model.valueobjects.Email;
import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.repository.StudentRepository;
import com.academic.enrollment.shared.annotation.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class StudentApplicationService implements CreateStudentUseCase,
                                                 UpdateStudentContactUseCase,
                                                 FindStudentsByStatusUseCase {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentApplicationService(StudentRepository studentRepository,
                                    StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponse create(CreateStudentCommand command) {
        // Validar que no exista un estudiante con el mismo DNI
        DNI dni = new DNI(command.getDni());
        if (studentRepository.existsByDni(dni)) {
            throw new IllegalArgumentException("Student with DNI " + command.getDni() + " already exists");
        }

        // Validar que no exista un estudiante con el mismo email
        Email email = new Email(command.getEmail());
        if (studentRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Student with email " + command.getEmail() + " already exists");
        }

        // Crear el estudiante
        Student student = Student.create(
                command.getFirstName(),
                command.getLastName(),
                dni,
                email,
                command.getPhone(),
                command.getBirthDate(),
                command.getAddress()
        );

        // Guardar el estudiante
        Student savedStudent = studentRepository.save(student);

        // Convertir a DTO de respuesta
        return studentMapper.toResponse(savedStudent);
    }

    @Override
    public StudentResponse updateContact(UpdateStudentContactCommand command) {
        // Buscar el estudiante
        Student student = studentRepository.findById(StudentId.of(command.getStudentId()))
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + command.getStudentId()));

        // Actualizar información de contacto
        student.updateContactInfo(command.getPhone(), command.getAddress());

        // Guardar cambios
        Student updatedStudent = studentRepository.save(student);

        // Convertir a DTO de respuesta
        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> findByStatus(FindStudentsByStatusQuery query) {
        boolean active = query.getActive() != null ? query.getActive() : true;

        List<Student> students = active ? studentRepository.findActiveStudents() : studentRepository.findAll();

        return students.stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
