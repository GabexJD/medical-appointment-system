package com.medicalscheduler.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "UserRequest", description = "Request body for creating a new patient user")
public class UserRequest {

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    @Schema(description = "Full name of the patient", example = "Jane Doe", required = true, maxLength = 100)
    private String fullName;

    @NotBlank(message = "Document number is required")
    @Size(max = 20, message = "Document number must be at most 20 characters")
    @Schema(description = "Government-issued document number", example = "12345678", required = true, maxLength = 20)
    private String documentNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    @Schema(description = "Email address of the patient", example = "jane.doe@email.com", required = true, maxLength = 100)
    private String email;

    @Size(max = 20, message = "Phone must be at most 20 characters")
    @Schema(description = "Phone number of the patient", example = "+1-555-0199", maxLength = 20)
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    @Schema(description = "Account password", example = "securePass123", required = true, minLength = 6, maxLength = 255)
    private String password;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
