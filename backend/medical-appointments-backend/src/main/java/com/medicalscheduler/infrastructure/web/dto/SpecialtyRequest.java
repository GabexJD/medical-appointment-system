package com.medicalscheduler.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SpecialtyRequest", description = "Request body for creating or updating a medical specialty")
public class SpecialtyRequest {

    @NotBlank(message = "Specialty name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    @Schema(description = "Name of the medical specialty", example = "Cardiology", required = true, maxLength = 100)
    private String name;

    @Size(max = 500, message = "Description must be at most 500 characters")
    @Schema(description = "Description of the medical specialty", example = "Diagnosis and treatment of heart diseases", maxLength = 500)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
