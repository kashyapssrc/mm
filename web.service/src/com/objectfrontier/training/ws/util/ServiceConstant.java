/**
 *
 */
package com.objectfrontier.training.ws.util;

/**
 * @author mohammed.mohammed
 * @since  Nov 17, 2018
 */
public interface ServiceConstant {

    String ID            = "id";
    String PERSON_ID     = "pers.id";
    String ID_COUNT      = "COUNT(id)";

    String STREET        = "street";
    String CITY          = "city";
    String POSTAL_CODE   = "postal_code";

    String FIRST_NAME    = "first_name";
    String LAST_NAME     = "last_name";
    String EMAIL         = "email";
    String BIRTH_DATE    = "birth_date";
    String CREATED_DATE  = "created_date";
    String ADDRESS_ID    = "address_id";
    String IS_ADMIN      = "is_admin";
    String PASSWORD      = "password";

    String FIND_USER_QUERY = "SELECT id FROM jdbc_person WHERE email = ? AND password = ?;";


    String INSERT_ADDRESS_QUERY = new StringBuilder("INSERT INTO jdbc_address").append("(street, city, postal_code)")
                                                                               .append(" VALUES (?,?,?);")
                                                                               .toString();

    String SEARCH_ADDRESS_QUERY_FORMAT = "SELECT %s FROM jdbc_address WHERE %s;";

    String READ_ADDRESS_QUERY = new StringBuilder("SELECT ").append("id, street, city, postal_code ")
                                                            .append("FROM jdbc_address ")
                                                            .append("WHERE id = ?;")
                                                            .toString();

    String READ_ALL_ADDRESS_QUERY = "SELECT id, street, city, postal_code FROM jdbc_address;";

    String UPDATE_ADDRESS_QUERY = new StringBuilder("UPDATE jdbc_address SET ").append("street = ?, ")
                                                                               .append("city = ?, ")
                                                                               .append("postal_code = ? ")
                                                                               .append("WHERE id = ?;")
                                                                               .toString();

    String DELETE_ADDRESS_QUERY = "DELETE FROM jdbc_address WHERE id = ?;";

    String PERSON_NAME_CHECK_QUERY_FOR_UPDATE = new StringBuilder("SELECT ").append("COUNT(id) ")
                                                                            .append("FROM jdbc_person ")
                                                                            .append("WHERE first_name = ? ")
                                                                            .append("AND last_name = ? ")
                                                                            .append("AND id != ?;")
                                                                            .toString();

    String PERSON_NAME_CHECK_QUERY = new StringBuilder("SELECT ").append("COUNT(id) ")
                                                                 .append("FROM jdbc_person ")
                                                                 .append("WHERE first_name = ? ")
                                                                 .append("AND last_name = ?;")
                                                                 .toString();

    String PERSON_EMAIL_CHECK_QUERY_FOR_UPDATE = new StringBuilder("SELECT ").append("COUNT(id) ")
                                                                             .append("FROM jdbc_person ")
                                                                             .append("WHERE email = ? ")
                                                                             .append("AND id != ?;")
                                                                             .toString();

    String PERSON_EMAIL_CHECK_QUERY = "SELECT COUNT(id) FROM jdbc_person WHERE email = ?;";

    String INSERT_PERSON_QUERY = new StringBuilder("INSERT ").append("INTO jdbc_person (")
                                                             .append("  first_name")
                                                             .append(", last_name")
                                                             .append(", email")
                                                             .append(", address_id")
                                                             .append(", birth_date")
                                                             .append(", is_admin")
                                                             .append(", password)")
                                                             .append(" VALUES (?, ?, ?, ?, ?, ?, ?);")
                                                             .toString();

    String SEARCH_PERSON_QUERY_FORMAT = new StringBuilder("SELECT").append(" %s ")
                                                                   .append("FROM jdbc_person AS pers ")
                                                                   .append("INNER JOIN jdbc_address AS addr ")
                                                                   .append("ON pers.id = addr.id ")
                                                                   .append("WHERE %s;")
                                                                   .toString();

    String READ_PERSON_QUERY = new StringBuilder("SELECT").append("  id")
                                                          .append(", first_name")
                                                          .append(", last_name")
                                                          .append(", email")
                                                          .append(", address_id")
                                                          .append(", birth_date")
                                                          .append(", is_admin")
                                                          .append(" FROM jdbc_person WHERE id = ?;")
                                                          .toString();

    String READ_ALL_PERSON_QUERY = new StringBuilder("SELECT").append("  id")
                                                              .append(", first_name")
                                                              .append(", last_name")
                                                              .append(", email")
                                                              .append(", address_id")
                                                              .append(", birth_date")
                                                              .append(", is_admin")
                                                              .append(" FROM jdbc_person;")
                                                              .toString();

    String UPDATE_PERSON_QUERY = new StringBuilder("UPDATE jdbc_person SET").append("  first_name = ?")
                                                                            .append(", last_name = ?")
                                                                            .append(", email = ?")
                                                                            .append(", birth_date = ?")
                                                                            .append(" WHERE id = ?;")
                                                                            .toString();

    String DELETE_PERSON_QUERY = "DELETE FROM jdbc_person WHERE id = ?;";
}
