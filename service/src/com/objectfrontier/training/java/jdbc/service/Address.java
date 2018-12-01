/**
 *
 */
package com.objectfrontier.training.java.jdbc.service;

/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
public class Address {
    long id;
    String street;
    String city;
    int postalCode;

    public Address() {}

    public Address(String street, String city, int postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%s\t%d", id, street, city, postalCode);
    }
}
