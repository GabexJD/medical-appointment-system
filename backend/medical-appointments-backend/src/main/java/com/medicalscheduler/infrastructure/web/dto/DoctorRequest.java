package com.medicalscheduler.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "DoctorRequest", description = "Request body for creating or updating a doctor")
public class DoctorRequest {

    @NotBlank(message = "Doctor full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    @Schema(description = "Full name of the doctor", example = "Dr. John Doe", required = true, maxLength = 100)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    @Schema(description = "Email address of the doctor", example = "john.doe@hospital.com", required = true, maxLength = 100)
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(max = 20, message = "Phone must be at most 20 characters")
    @Schema(description = "Phone number of the doctor", example = "+1-555-0123", required = true, maxLength = 20)
    private String phone;

    @NotNull(message = "Specialty ID is required")
    @Schema(description = "ID of the medical specialty this doctor belongs to", example = "1", required = true)
    private Integer specialtyId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }
}
