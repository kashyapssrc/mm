package com.objectfrontier.training.java.jdbc;

import java.sql.Date;
import java.sql.Timestamp;

public class Person {

    Long id;
    String name;
    String email;
    Long addressId;
    Date birthDate;
    Timestamp createdDate;

    public Person() {
    }

    public Person(String name, String email, Long addressId, Date birthDate) {
        this.name = name;
        this.email = email;
        this.addressId = addressId;
        this.birthDate = birthDate;
    }

    public Person(String name, String email, Date birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
