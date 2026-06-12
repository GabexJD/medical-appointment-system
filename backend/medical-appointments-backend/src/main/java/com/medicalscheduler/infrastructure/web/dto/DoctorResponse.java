package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "DoctorResponse", description = "Response body representing a doctor")
public class DoctorResponse {

    @Schema(description = "Unique identifier of the doctor", example = "1")
    private Integer id;

    @Schema(description = "Full name of the doctor", example = "Dr. John Doe")
    private String fullName;

    @Schema(description = "Email address of the doctor", example = "john.doe@hospital.com")
    private String email;

    @Schema(description = "Phone number of the doctor", example = "+1-555-0123")
    private String phone;

    @Schema(description = "ID of the associated medical specialty", example = "1")
    private Integer specialtyId;

    @Schema(description = "Name of the associated medical specialty", example = "Cardiology")
    private String specialtyName;

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

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
