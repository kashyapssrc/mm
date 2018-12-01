/**
 *
 */
package com.objectfrontier.training.ws.exception;

/**
 * @author mohammed.mohammed
 * @since  Oct 13, 2018
 */
public enum AppErrorCode {

    ERROR_NOT_AUTO_GEN_ADDR_ID        ("Address Id is not auto generated in the database"),
    ERROR_NULL_STREET                 ("street name cannot be NULL"),
    ERROR_NULL_CITY                   ("city name cannot be NULL"),
    ERROR_NULL_POSTAL_CODE            ("postal code cannot be NULL"),

    ERROR_NOT_AUTO_GEN_PERSON_ID      ("Person Id is not auto generated in the database"),
    ERROR_NULL_FIRST_NAME             ("Person first name cannot be NULL"),
    ERROR_NULL_LAST_NAME              ("Person last name cannot be NULL"),
    ERROR_DUPL_NAME                   ("Name already exist"),
    ERROR_NULL_EMAIL                  ("Person email cannot be NULL"),
    ERROR_DUPL_EMAIL                  ("Email id already exist"),
    ERROR_NULL_BIRTH_DATE             ("Person birth date cannot be NULL"),

    ERROR_NO_RECORD_FOUND             ("No record found"),
    ERROR_INVALID_DATA_FORMAT         ("Invalid input data format error"),

    ERROR_DATABASE                    ("Database error"),
    ERROR_CONNECTION_FAILED           ("Connection error"),
    ERROR_CREATE_FAILED               ("Insertion error"),
    ERROR_READ_FAILED                 ("Read record error"),
    ERROR_READ_ALL_FAILED             ("Read all records error"),
    ERROR_SEARCH_FAILED               ("Search record error"),
    ERROR_UPDATE_FAILED               ("Updation error"),
    ERROR_DELETE_FAILED               ("Deletion error"),

    UNKNOWN_ERROR                     ("Unknown error in execution"),
    LOAD_JSON_ERROR                   ("Error in loading Json"),
    STORE_JSON_ERROR                  ("Error in storing Json"),

    ERROR_AUTHENTICATION_FAILED       ("Authentication error"),
    ERROR_INVALID_USER                ("Invalid user"),
    ERROR_ACCESS_DENIED               ("Not an authorized user for this service"),
    ERROR_NOT_LOGGED_IN               ("User not logged in"),
    ERROR_INTERNAL_SERVER             ("Problem in server");


    private String errorMessage;

    private AppErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCodeMessage() {
        return this.errorMessage;
    }
}
