package com.medicalscheduler.infrastructure.web.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(name = "TimeSlot", description = "An available time slot for a doctor appointment")
public class TimeSlot {

    @Schema(description = "Date of the slot", example = "2026-06-15")
    private LocalDate date;

    @Schema(description = "Day of the week", example = "MONDAY")
    private String dayOfWeek;

    @Schema(description = "Start time of the slot", example = "09:00")
    private LocalTime time;

    @Schema(description = "Indicates if the slot is free for booking", example = "true")
    private boolean available;

    public TimeSlot() {
    }

    public TimeSlot(LocalDate date, String dayOfWeek, LocalTime time, boolean available) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.available = available;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
