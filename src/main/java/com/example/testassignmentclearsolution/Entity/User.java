package com.example.testassignmentclearsolution.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String second_name;

    @Column(name = "birth_date")
    private String birth_date;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phone_number;

    public User() {

    }

    public User(Long id, String email, String name, String second_name, String birth_date) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.second_name = second_name;
        this.birth_date = birth_date;
    }

    public User(Long id, String email, String name, String second_name, String birth_date, String address, String phone_number) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.second_name = second_name;
        this.birth_date = birth_date;
        this.address = address;
        this.phone_number = phone_number;
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

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String secondName) {
        this.second_name = secondName;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birthDate) {
        this.birth_date = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phoneNumber) {
        this.phone_number = phoneNumber;
    }
}
