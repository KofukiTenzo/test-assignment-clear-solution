package com.example.testassignmentclearsolution.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class AddUserDTO {

    @Email
    @NotBlank(message = "*required field")
    private String email;

    @NotBlank(message = "*required field")
    private String name;

    @NotBlank(message = "*required field")
    private String second_name;

    @NotBlank(message = "*required field")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birth_date;

    private String address;

    private String phone_number;

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
        return second_name;
    }

    public void setSecondName(String secondName) {
        this.second_name = secondName;
    }

    public LocalDate getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birth_date = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }
}
