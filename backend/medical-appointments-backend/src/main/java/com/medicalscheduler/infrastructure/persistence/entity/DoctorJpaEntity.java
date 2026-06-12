package com.medicalscheduler.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Doctor")
public class DoctorJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fullName", nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialtyId", nullable = false)
    private SpecialtyJpaEntity specialty;

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

    public SpecialtyJpaEntity getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyJpaEntity specialty) {
        this.specialty = specialty;
    }
}
