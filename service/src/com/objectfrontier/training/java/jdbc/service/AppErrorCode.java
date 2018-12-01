/**
 *
 */
package com.objectfrontier.training.java.jdbc.service;

/**
 * @author mohammed.mohammed
 * @since  Oct 13, 2018
 */
public enum AppErrorCode {

    ERROR_NOT_AUTO_GEN_ADDR_ID   ("Address Id is not auto generated in the database"),
    ERROR_NULL_STREET            ("street name cannot be NULL"),
    ERROR_NULL_CITY              ("city name cannot be NULL"),
    ERROR_NULL_POSTAL_CODE       ("postal code cannot be NULL"),

    ERROR_NOT_AUTO_GEN_PERSON_ID ("Person Id is not auto generated in the database"),
    ERROR_NULL_FIRST_NAME        ("Person first name cannot be NULL"),
    ERROR_NULL_LAST_NAME         ("Person last name cannot be NULL"),
    ERROR_DUPL_NAME              ("Name already exist"),
    ERROR_NULL_EMAIL             ("Person email cannot be NULL"),
    ERROR_DUPL_EMAIL             ("Email id already exist"),
    ERROR_NULL_BIRTH_DATE        ("Person birth date cannot be NULL"),

    ERROR_DATABASE               ("Database error");

    private String errorMessage;

    private AppErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCodeMessage() {
        return this.errorMessage;
    }
}
