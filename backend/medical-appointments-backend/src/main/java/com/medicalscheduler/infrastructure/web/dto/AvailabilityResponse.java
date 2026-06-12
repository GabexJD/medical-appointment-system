package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "AvailabilityResponse", description = "Available appointment slots for a specific doctor")
public class AvailabilityResponse {

    @Schema(description = "ID of the doctor", example = "1")
    private Integer doctorId;

    @Schema(description = "Full name of the doctor", example = "Dr. Carlos Mendoza")
    private String doctorFullName;

    @Schema(description = "List of available time slots")
    private List<TimeSlot> availableSlots;

    public AvailabilityResponse() {
    }

    public AvailabilityResponse(Integer doctorId, String doctorFullName, List<TimeSlot> availableSlots) {
        this.doctorId = doctorId;
        this.doctorFullName = doctorFullName;
        this.availableSlots = availableSlots;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public List<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<TimeSlot> availableSlots) {
        this.availableSlots = availableSlots;
    }
}
