package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "UserResponse", description = "Response body representing a patient user")
public class UserResponse {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Integer id;

    @Schema(description = "Full name of the patient", example = "Jane Doe")
    private String fullName;

    @Schema(description = "Government-issued document number", example = "12345678")
    private String documentNumber;

    @Schema(description = "Email address of the patient", example = "jane.doe@email.com")
    private String email;

    @Schema(description = "Phone number of the patient", example = "+1-555-0199")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
