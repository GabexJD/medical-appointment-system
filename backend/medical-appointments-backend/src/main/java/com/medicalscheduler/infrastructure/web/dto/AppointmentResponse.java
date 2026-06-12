package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Schema(name = "AppointmentResponse", description = "Response body representing a scheduled appointment")
public class AppointmentResponse {

    @Schema(description = "Unique identifier of the appointment", example = "1")
    private Integer id;

    @Schema(description = "ID of the patient", example = "1")
    private Integer userId;

    @Schema(description = "Full name of the patient", example = "Jane Doe")
    private String userFullName;

    @Schema(description = "ID of the doctor", example = "2")
    private Integer doctorId;

    @Schema(description = "Full name of the doctor", example = "Dr. Laura Rodr\u00edguez")
    private String doctorFullName;

    @Schema(description = "ID of the medical specialty", example = "1")
    private Integer specialtyId;

    @Schema(description = "Name of the medical specialty", example = "Cardiolog\u00eda")
    private String specialtyName;

    @Schema(description = "Date of the appointment", example = "2026-06-15")
    private LocalDate appointmentDate;

    @Schema(description = "Time of the appointment", example = "09:00")
    private LocalTime appointmentTime;

    @Schema(description = "Current status of the appointment", example = "PENDING")
    private String status;

    @Schema(description = "Timestamp when the appointment was created", example = "2026-06-12T10:30:00")
    private LocalDateTime createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
