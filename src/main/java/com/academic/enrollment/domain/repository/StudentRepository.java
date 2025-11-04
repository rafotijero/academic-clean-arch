package com.academic.enrollment.domain.repository;

import com.academic.enrollment.domain.model.Student;
import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.DNI;
import com.academic.enrollment.domain.model.valueobjects.Email;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    Optional<Student> findById(StudentId id);

    Optional<Student> findByDni(DNI dni);

    Optional<Student> findByEmail(Email email);

    List<Student> findAll();

    List<Student> findActiveStudents();

    void delete(StudentId id);

    boolean existsByDni(DNI dni);

    boolean existsByEmail(Email email);
}
