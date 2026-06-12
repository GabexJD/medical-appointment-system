package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "LoginResponse", description = "Response body for successful login")
public class LoginResponse {

    @Schema(description = "Unique identifier of the authenticated user", example = "1")
    private Integer id;

    @Schema(description = "Full name of the authenticated user", example = "Jane Doe")
    private String fullName;

    public LoginResponse() {
    }

    public LoginResponse(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

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
}
