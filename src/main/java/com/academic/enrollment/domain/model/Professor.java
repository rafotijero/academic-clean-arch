package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.ProfessorId;
import com.academic.enrollment.domain.model.valueobjects.Email;
import com.academic.enrollment.domain.model.valueobjects.DNI;
import java.time.LocalDate;
import java.util.Objects;

public class Professor {
    private final ProfessorId id;
    private String firstName;
    private String lastName;
    private final DNI dni;
    private final Email email;
    private String phone;
    private String specialty;
    private String academicDegree;
    private LocalDate registrationDate;
    private boolean active;

    public Professor(ProfessorId id, String firstName, String lastName, DNI dni, Email email,
                     String phone, String specialty, String academicDegree, LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "Professor ID cannot be null");
        this.dni = Objects.requireNonNull(dni, "DNI cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.phone = phone;
        this.specialty = specialty;
        this.academicDegree = academicDegree;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
        this.active = true;
    }

    public static Professor create(String firstName, String lastName, DNI dni, Email email,
                                   String phone, String specialty, String academicDegree) {
        return new Professor(null, firstName, lastName, dni, email,
                phone, specialty, academicDegree, LocalDate.now());
    }

    public void updateContactInfo(String phone) {
        this.phone = phone;
    }

    public void updateAcademicInfo(String specialty, String academicDegree) {
        this.specialty = specialty;
        this.academicDegree = academicDegree;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    private void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (firstName.length() > 50) {
            throw new IllegalArgumentException("First name cannot exceed 50 characters");
        }
        this.firstName = firstName.trim();
    }

    private void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (lastName.length() > 50) {
            throw new IllegalArgumentException("Last name cannot exceed 50 characters");
        }
        this.lastName = lastName.trim();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Getters
    public ProfessorId getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public DNI getDni() { return dni; }
    public Email getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getSpecialty() { return specialty; }
    public String getAcademicDegree() { return academicDegree; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(id, professor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
