package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SpecialtyResponse", description = "Response body representing a medical specialty")
public class SpecialtyResponse {

    @Schema(description = "Unique identifier of the specialty", example = "1")
    private Integer id;

    @Schema(description = "Name of the medical specialty", example = "Cardiology")
    private String name;

    @Schema(description = "Description of the medical specialty", example = "Diagnosis and treatment of heart diseases")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
