package com.academic.enrollment.application.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateStudentContactCommand {

    @NotNull(message = "Student ID is required")
    private String studentId;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    public UpdateStudentContactCommand() {}

    public UpdateStudentContactCommand(String studentId, String phone, String address) {
        this.studentId = studentId;
        this.phone = phone;
        this.address = address;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
