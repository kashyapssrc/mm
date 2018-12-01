/**
 *
 */
package com.objectfrontier.training.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author mohammed.mohammed
 * @since  Sep 22, 2018
 */
public class AddressService {

    public long insertAddressValue(Address addressDetails, Connection connection) throws Exception {
        if(addressDetails.postalCode == 0) {
            throw new RuntimeException("postal_code cannot be NULL");
        }

        String insertQuery = "INSERT INTO jdbc_address(street" +
                                                    ", city" +
                                                    ", postal_code)" +
                             " VALUES (?,?,?);";
        PreparedStatement insertStatement =  connection.prepareStatement(insertQuery);

        insertStatement.setString   (1, addressDetails.street);
        insertStatement.setString   (2, addressDetails.city);
        insertStatement.setLong     (3, addressDetails.postalCode);
        insertStatement.executeUpdate();

        PreparedStatement addressIdStatement = connection.prepareStatement("SELECT id FROM jdbc_address WHERE street = ?;");
        addressIdStatement.setString(1, addressDetails.street);
        ResultSet addressIdResult = addressIdStatement.executeQuery();
        addressIdResult.next();

        Address address = new Address();
        address.setId(addressIdResult.getLong(1));
        long addressId = address.getId();
        addressIdResult.close();
        addressIdStatement.close();

        insertStatement.close();
        return addressId;
    }

    public String readAddressValue(String readQuery, Connection connection) throws Exception {
        PreparedStatement readStatement = connection.prepareStatement(readQuery);
        ResultSet result = readStatement.executeQuery();

        result.next();
        Address address = new Address();
        address.setId         (result.getLong(1));
        address.setStreet     (result.getString(2));
        address.setCity       (result.getString(3));
        address.setPostalCode (result.getInt(4));

        String addressRecord = address.getId() + "\t" +
                               address.getStreet() + "\t" +
                               address.getCity() + "\t" +
                               address.getPostalCode();

        result.close();
        readStatement.close();
        return addressRecord ;
    }

    public int readAllAddressValue(String readAllQuery, Connection connection) throws Exception {
        PreparedStatement readStatement = connection.prepareStatement(readAllQuery);
        ResultSet result = readStatement.executeQuery();

        int rowCount = 0;
        Address address = new Address();

        while (result.next()) {
            address.setId         (result.getLong(1));
            address.setStreet     (result.getString(2));
            address.setCity       (result.getString(3));
            address.setPostalCode (result.getInt(4));

            String addressRecord = address.getId() + "\t" +
                                   address.getStreet() + "\t" +
                                   address.getCity() + "\t" +
                                   address.getPostalCode();
            System.out.println(addressRecord);
            rowCount++;
        }

        result.close();
        readStatement.close();
        return rowCount;
    }

    public int updateAddressValue(String updateQuery, Connection connection) throws Exception {
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

        int rowsAffected = updateStatement.executeUpdate();
        updateStatement.close();
        return rowsAffected;
    }

    public int deleteAddressValue(String deleteQuery, Connection connection) throws Exception {
        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);

        int rowsAffected = deleteStatement.executeUpdate();
        deleteStatement.close();
        return rowsAffected;
    }
}
