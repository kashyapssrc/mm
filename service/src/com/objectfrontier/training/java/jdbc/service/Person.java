/**
 *
 */
package com.objectfrontier.training.java.jdbc.service;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
public class Person {
    long id;
    String firstName;
    String lastName;
    String email;
    Date birthDate;
    Timestamp createdDate;
    Address address;

    public Person() {}

    public Person(String firstName, String lastName, String email, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\t%s\t%s\t%s\t%s\t%s\t%s",
                id, firstName, lastName, email, birthDate, createdDate, address);
    }


}
