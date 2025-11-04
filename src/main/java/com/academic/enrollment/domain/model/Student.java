package com.academic.enrollment.domain.model;

import com.academic.enrollment.domain.model.valueobjects.StudentId;
import com.academic.enrollment.domain.model.valueobjects.Email;
import com.academic.enrollment.domain.model.valueobjects.DNI;
import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private final StudentId id;
    private String firstName;
    private String lastName;
    private final DNI dni;
    private final Email email;
    private String phone;
    private LocalDate birthDate;
    private String address;
    private LocalDate registrationDate;
    private boolean active;

    public Student(StudentId id, String firstName, String lastName, DNI dni, Email email,
                   String phone, LocalDate birthDate, String address, LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "Student ID cannot be null");
        this.dni = Objects.requireNonNull(dni, "DNI cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.phone = phone;
        this.birthDate = Objects.requireNonNull(birthDate, "Birth date cannot be null");
        this.address = address;
        this.registrationDate = registrationDate != null ? registrationDate : LocalDate.now();
        this.active = true;
    }

    public static Student create(String firstName, String lastName, DNI dni, Email email,
                                 String phone, LocalDate birthDate, String address) {
        return new Student(null, firstName, lastName, dni, email,
                phone, birthDate, address, LocalDate.now());
    }

    public void updateContactInfo(String phone, String address) {
        this.phone = phone;
        this.address = address;
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

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    // Getters
    public StudentId getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public DNI getDni() { return dni; }
    public Email getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getAddress() { return address; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
