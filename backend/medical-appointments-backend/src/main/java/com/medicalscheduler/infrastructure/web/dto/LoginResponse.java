package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "LoginResponse", description = "Response body for successful login")
public class LoginResponse {

    @Schema(description = "Unique identifier of the authenticated user", example = "1")
    private Integer userId;

    @Schema(description = "Full name of the authenticated user", example = "Jane Doe")
    private String fullName;

    public LoginResponse() {
    }

    public LoginResponse(Integer userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
