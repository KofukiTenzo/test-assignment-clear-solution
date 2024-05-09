package com.example.testassignmentclearsolution.Response;

import jakarta.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ResponseUser {
    private Long id;

    private String email;

    private String name;

    private String secondName;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

    public ResponseUser() {
    }

    public ResponseUser(Long id, String email, String name, String secondName, LocalDate birthDate, String address, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
