package com.objectfrontier.training.java.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 */

/**
 * @author mohammed.mohammed
 * @since  Sep 24, 2018
 */
public class PersonService {

    public int insertPersonValue(Person personDetails,
                                 Address addressDetails,
                                 Connection connection) throws Exception {

        int insertedRows = 0;
        if (addressDetails == null) {
            String emailCheckQuery = "SELECT * FROM jdbc_person WHERE email = ?";
            PreparedStatement emailCheck = connection.prepareStatement(emailCheckQuery);
            emailCheck.setString(1, personDetails.email);
            ResultSet emailCheckResultSet = emailCheck.executeQuery();

            if (emailCheckResultSet.next()) {
                throw new RuntimeException("Email id cannot be duplicate");
            }

            String insertQuery = "INSERT INTO jdbc_person(name" +
                                                       ", email" +
                                                       ", address_id" +
                                                       ", birth_date) " +
                                 "VALUES (?, ?, ?, ?);";

            PreparedStatement insertStatement =  connection.prepareStatement(insertQuery);

            insertStatement.setString    (1, personDetails.name);
            insertStatement.setString    (2, personDetails.email);
            insertStatement.setLong      (3, personDetails.addressId);
            insertStatement.setDate      (4, personDetails.birthDate);

            insertedRows = insertStatement.executeUpdate();
            insertStatement.close();

        } else {
            AddressService addressService = new AddressService();
            long addressId = addressService.insertAddressValue(addressDetails, connection);

            String emailCheckQuery = "SELECT * FROM jdbc_person WHERE email = ?;";
            PreparedStatement emailCheck = connection.prepareStatement(emailCheckQuery);
            emailCheck.setString(1, personDetails.email);
            ResultSet emailCheckResultSet = emailCheck.executeQuery();

            if (emailCheckResultSet.next()) {
                throw new RuntimeException("Email id cannot be duplicate");
            }

            String insertQuery = "INSERT INTO jdbc_person(name" +
                                                       ", email" +
                                                       ", address_id" +
                                                       ", birth_date) " +
                                 "VALUES (?, ?, ?, ?);";

            PreparedStatement insertStatement =  connection.prepareStatement(insertQuery);

            insertStatement.setString    (1, personDetails.name);
            insertStatement.setString    (2, personDetails.email);
            insertStatement.setLong      (3, addressId);
            insertStatement.setDate      (4, personDetails.birthDate);

            insertedRows = insertStatement.executeUpdate();
            insertStatement.close();
        }

        return insertedRows;
    }

    public String readPersonValue(String readQuery, Connection connection) throws Exception {
        PreparedStatement readStatement = connection.prepareStatement(readQuery);
        ResultSet result = readStatement.executeQuery();

        result.next();
        Person person = new Person();
        person.setId          (result.getLong(1));
        person.setName        (result.getString(2));
        person.setEmail       (result.getString(3));
        person.setAddressId   (result.getLong(4));
        person.setBirthDate   (result.getDate(5));
        person.setCreatedDate (result.getTimestamp(6));

        String addressRecord = person.getId() + "\t" +
                               person.getName() + "\t" +
                               person.getEmail() + "\t" +
                               person.getAddressId() + "\t" +
                               person.getBirthDate() + "\t" +
                               person.getCreatedDate();

        result.close();
        readStatement.close();
        return addressRecord ;
    }

    public int readAllPersonValue(boolean includeAddress, Connection connection) throws Exception {
        int recordRead = 0;
        if (includeAddress == true) {
            StringBuilder queryBuilder = new StringBuilder();
            String joinQuery = queryBuilder.append("SELECT pers.id, name, email, address_id, birth_date, created_date, ")
                                           .append("street, city, postal_code ")
                                           .append("FROM jdbc_person AS pers LEFT JOIN jdbc_address AS addr ")
                                           .append("ON pers.address_id = addr.id;")
                                           .toString();

            PreparedStatement readStatement = connection.prepareStatement(joinQuery);
            ResultSet joinResult = readStatement.executeQuery();

            int rowCount = 0;
            Person person = new Person();
            Address address = new Address();

            while (joinResult.next()) {
                person.setId          (joinResult.getLong(1));
                person.setName        (joinResult.getString(2));
                person.setEmail       (joinResult.getString(3));
                person.setAddressId   (joinResult.getLong(4));
                person.setBirthDate   (joinResult.getDate(5));
                person.setCreatedDate (joinResult.getTimestamp(6));
                address.setStreet     (joinResult.getString(7));
                address.setCity       (joinResult.getString(8));
                address.setPostalCode (joinResult.getInt(9));

                String personAddressRecord = person.getId() + "\t" +
                                             person.getName() + "\t" +
                                             person.getEmail() + "\t" +
                                             person.getAddressId() + "\t" +
                                             person.getBirthDate() + "\t" +
                                             person.getCreatedDate() + "\t" +
                                             address.getStreet() + "\t" +
                                             address.getCity() + "\t" +
                                             address.getPostalCode();

                System.out.println(personAddressRecord);
                rowCount++;
            }
            recordRead = rowCount;

        } else {
            String readAllPersonQuery = "SELECT * FROM jdbc_person;";
            PreparedStatement readStatement = connection.prepareStatement(readAllPersonQuery);
            ResultSet result = readStatement.executeQuery();

            int rowCount = 0;
            Person person = new Person();

            while (result.next()) {
                person.setId          (result.getLong(1));
                person.setName        (result.getString(2));
                person.setEmail       (result.getString(3));
                person.setAddressId   (result.getLong(4));
                person.setBirthDate   (result.getDate(5));
                person.setCreatedDate (result.getTimestamp(6));

                String personRecord = person.getId() + "\t" +
                        person.getName() + "\t" +
                        person.getEmail() + "\t" +
                        person.getAddressId() + "\t" +
                        person.getBirthDate() + "\t" +
                        person.getCreatedDate();
                System.out.println(personRecord);
                rowCount++;
            }
            recordRead = rowCount;
        }

        return recordRead;
    }

    public int updatePersonValue(String updateQuery, Connection connection) throws Exception {
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

        int rowsAffected = updateStatement.executeUpdate();
        updateStatement.close();
        return rowsAffected;
    }

    public int deletePersonValue(String deleteQuery, Connection connection) throws Exception {
        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);

        int rowsAffected = deleteStatement.executeUpdate();
        deleteStatement.close();
        return rowsAffected;
    }
}
