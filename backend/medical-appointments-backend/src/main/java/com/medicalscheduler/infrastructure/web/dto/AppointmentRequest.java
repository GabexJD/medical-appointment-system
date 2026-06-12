package com.medicalscheduler.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(name = "AppointmentRequest", description = "Request body for booking a new appointment")
public class AppointmentRequest {

    @NotNull(message = "User ID is required")
    @Schema(description = "ID of the patient", example = "1", required = true)
    private Integer userId;

    @NotNull(message = "Doctor ID is required")
    @Schema(description = "ID of the doctor", example = "2", required = true)
    private Integer doctorId;

    @NotNull(message = "Appointment date is required")
    @Schema(description = "Date of the appointment", example = "2026-06-15", required = true)
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    @Schema(description = "Time of the appointment (15-minute intervals)", example = "09:00", required = true)
    private LocalTime appointmentTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
