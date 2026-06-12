package com.medicalscheduler.domain.entity;

import java.util.Objects;

public class Doctor {

    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private Specialty specialty;

    public Doctor() {
    }

    public Doctor(Integer id, String fullName, String email, String phone, Specialty specialty) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
