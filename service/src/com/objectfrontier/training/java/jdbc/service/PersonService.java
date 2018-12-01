/**
 *
 */
package com.objectfrontier.training.java.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
public class PersonService {

    private PersonService() {}

    private AddressService addressService = new AddressService();
    public PersonService(AddressService addressService) {
        this.addressService = addressService;
    }

    private boolean isEmpty(String stringToBeChecked) {
        return Objects.isNull(stringToBeChecked) || stringToBeChecked.equals("");
    }

    private List<AppErrorCode> validatePerson(Person personDetails, boolean forUpdate, Connection connection) throws Exception {

        List<AppErrorCode> exceptionList = new ArrayList<>();

        if (isEmpty(personDetails.firstName)) {
            exceptionList.add(AppErrorCode.ERROR_NULL_FIRST_NAME);
        }

        if (isEmpty(personDetails.lastName)) {
            exceptionList.add(AppErrorCode.ERROR_NULL_LAST_NAME);
        }

        String nameCheckQuery;
        ResultSet nameCheckResultSet;
        try {
            if (forUpdate == true) {
                nameCheckQuery = "SELECT COUNT(id) FROM jdbc_person WHERE first_name = ? AND last_name = ? AND id != ?;";
                PreparedStatement nameCheck = connection.prepareStatement(nameCheckQuery);
                nameCheck.setString(1, personDetails.firstName);
                nameCheck.setString(2, personDetails.lastName);
                nameCheck.setLong(3, personDetails.id);
                nameCheckResultSet = nameCheck.executeQuery();
            } else {
                nameCheckQuery = "SELECT COUNT(id) FROM jdbc_person WHERE first_name = ? AND last_name = ?;";
                PreparedStatement nameCheck = connection.prepareStatement(nameCheckQuery);
                nameCheck.setString(1, personDetails.firstName);
                nameCheck.setString(2, personDetails.lastName);
                nameCheckResultSet = nameCheck.executeQuery();
            }
            nameCheckResultSet.next();
            int duplicateNameCount = nameCheckResultSet.getInt(1);
            if (duplicateNameCount > 0) {
                exceptionList.add(AppErrorCode.ERROR_DUPL_NAME);
            }
        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        if (isEmpty(personDetails.email)) {
            exceptionList.add(AppErrorCode.ERROR_NULL_EMAIL);
        }

        String emailCheckQuery;
        ResultSet emailCheckResultSet;
        try {
            if (forUpdate == true) {
                emailCheckQuery = "SELECT COUNT(id) FROM jdbc_person WHERE email = ? AND id != ?;";
                PreparedStatement emailCheck = connection.prepareStatement(emailCheckQuery);
                emailCheck.setString(1, personDetails.email);
                emailCheck.setLong(2, personDetails.id);
                emailCheckResultSet = emailCheck.executeQuery();
            } else {
                emailCheckQuery = "SELECT COUNT(id) FROM jdbc_person WHERE email = ?;";
                PreparedStatement emailCheck = connection.prepareStatement(emailCheckQuery);
                emailCheck.setString(1, personDetails.email);
                emailCheckResultSet = emailCheck.executeQuery();
            }
            emailCheckResultSet.next();
            int duplicateEmailCount = emailCheckResultSet.getInt(1);
            if (duplicateEmailCount > 0) {
                exceptionList.add(AppErrorCode.ERROR_DUPL_EMAIL);
            }
        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        if (Objects.isNull(personDetails.birthDate)) {
            exceptionList.add(AppErrorCode.ERROR_NULL_BIRTH_DATE);
        }

        return exceptionList;
    }

    public Person createPerson(Person personDetails,
                               Address addressDetails,
                               Connection connection) throws Exception {

        List<AppErrorCode> exceptionList = validatePerson(personDetails, false, connection);
        if (addressDetails != null) {
            try {
                Address address = addressService.createAddress(addressDetails, connection);
                personDetails.setAddress(address);
            } catch (AppException appExcp) {
                exceptionList.addAll(appExcp.getExceptionList());
            }
        }

        if(!exceptionList.isEmpty()) {
            throw new AppException(exceptionList);
        }

        String insertQuery = new StringBuilder("INSERT INTO jdbc_person")
                                       .append("(first_name, last_name, email, address_id, birth_date)")
                                       .append(" VALUES (?, ?, ?, ?, ?);")
                                       .toString();

        try {
            PreparedStatement insertStatement =  connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString    (1, personDetails.firstName);
            insertStatement.setString    (2, personDetails.lastName);
            insertStatement.setString    (3, personDetails.email);
            insertStatement.setLong      (4, personDetails.address.id);
            insertStatement.setDate      (5, personDetails.birthDate);
            insertStatement.executeUpdate();

            ResultSet autoGeneratedId = insertStatement.getGeneratedKeys();
            autoGeneratedId.next();
            personDetails.setId(autoGeneratedId.getLong(1));

            if (personDetails.id == 0) {
                throw new AppException(AppErrorCode.ERROR_NOT_AUTO_GEN_PERSON_ID);
            }
            insertStatement.close();

        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        return personDetails;
    }

    public List<Person> searchPerson(String[] searchField,
                                     String searchKeyword,
                                     Connection connection,
                                     String ...resultSetColumn) throws Exception {

        String[] databaseColumn = new String[resultSetColumn.length];
        int index = 0;

        for (String resultColumn : resultSetColumn) {
            if (resultColumn.equalsIgnoreCase("id")) databaseColumn[index] = "id";
            if (resultColumn.equalsIgnoreCase("first name")) databaseColumn[index] = "first_name";
            if (resultColumn.equalsIgnoreCase("last name")) databaseColumn[index] = "last_name";
            if (resultColumn.equalsIgnoreCase("email")) databaseColumn[index] = "email";
            if (resultColumn.equalsIgnoreCase("birthday")) databaseColumn[index] = "birth_date";
            index++;
        }

        String column = Arrays.toString(databaseColumn);
        String resultantColumn = column.substring(1, column.length() - 1);

        String[] searchFieldArray = new String[searchField.length];
        int searchFieldIndex = 0;
        for (String searchFieldName : searchField) {
            if (searchFieldName.equalsIgnoreCase("id")) searchFieldArray[searchFieldIndex] = "id";
            if (searchFieldName.equalsIgnoreCase("first name")) searchFieldArray[searchFieldIndex] = "first_name";
            if (searchFieldName.equalsIgnoreCase("last name")) searchFieldArray[searchFieldIndex] = "last_name";
            if (searchFieldName.equalsIgnoreCase("email")) searchFieldArray[searchFieldIndex] = "email";
            if (searchFieldName.equalsIgnoreCase("birthday")) searchFieldArray[searchFieldIndex] = "birth_date";
            searchFieldIndex++;
        }

        String searchQuery = String.format("SELECT %s FROM jdbc_person WHERE %s;",
                                           resultantColumn,
                                           constructQuery(searchFieldArray));
        List<Person> resultList = new ArrayList<>();

        try {
            PreparedStatement searchStatement = connection.prepareStatement(searchQuery);

            for (int fieldIndex = 1; fieldIndex <= searchFieldArray.length; fieldIndex++) {
                searchStatement.setString(fieldIndex,"%" + searchKeyword + "%");
            }
            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                Person personDetails = new Person();

                for(String columnName : databaseColumn) {
                    if (columnName.equalsIgnoreCase("id")) {
                        personDetails.setId(searchResult.getLong(columnName));
                    }
                    if (columnName.equalsIgnoreCase("first_name")) {
                        personDetails.setFirstName(searchResult.getString(columnName));
                    }
                    if (columnName.equalsIgnoreCase("last_name")) {
                        personDetails.setLastName(searchResult.getString(columnName));
                    }
                    if (columnName.equalsIgnoreCase("email")) {
                        personDetails.setEmail(searchResult.getString(columnName));
                    }
                    if (columnName.equalsIgnoreCase("address_id")) {
                        Address addressId = new Address();
                        addressId.setId(searchResult.getLong(columnName));
                        personDetails.setAddress(addressId);
                    }
                    if (columnName.equalsIgnoreCase("birth_date")) {
                        personDetails.setBirthDate(searchResult.getDate(columnName));
                    }
                    if (columnName.equalsIgnoreCase("created_date")) {
                        personDetails.setCreatedDate(searchResult.getTimestamp(columnName));
                    }
                }
                resultList.add(personDetails);
            }

        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        return resultList;
    }

    private String constructQuery(String[] fields) {
        StringBuilder sb = new StringBuilder();
        for(String fieldName : fields) {
            sb.append(fieldName + " LIKE ? OR ");
        }
        String searchFieldQuery = sb.subSequence(0, sb.length() - 4).toString();
        System.out.println(searchFieldQuery);
        return searchFieldQuery;
    }

    public Person readPerson(Person personId, boolean includeAddress, Connection connection) throws Exception {

        String readQuery = new StringBuilder("SELECT ").append("id, first_name, last_name, email, address_id, birth_date ")
                                                       .append("FROM jdbc_person WHERE ")
                                                       .append("id = ?;")
                                                       .toString();
        Person personDetails = new Person();

        try {
            PreparedStatement readStatement = connection.prepareStatement(readQuery);
            readStatement.setLong(1, personId.id);
            ResultSet readPersonResultSet = readStatement.executeQuery();

            readPersonResultSet.next();
            personDetails = constructPerson(readPersonResultSet);
            if (includeAddress == true) {
                Address addressDetails = addressService.readAddress(personDetails.address, connection);
                personDetails.setAddress(addressDetails);
            }

            readPersonResultSet.close();
            readStatement.close();

        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        return personDetails ;
    }

    public List<Person> readAllPerson(boolean includeAddress, Connection connection) throws Exception {

        String readAllQuery = new StringBuilder("SELECT ").append("id, first_name, last_name, email, address_id, birth_date")
                                                          .append(" FROM jdbc_person;")
                                                          .toString();
        List<Person> recordList = new ArrayList<>();

        try {
            PreparedStatement readStatement = connection.prepareStatement(readAllQuery);
            ResultSet readAllPersonResultSet = readStatement.executeQuery();

            while (readAllPersonResultSet.next()) {
                Person personDetails = constructPerson(readAllPersonResultSet);

                if (includeAddress == true) {
                    Address addressDetails = addressService.readAddress(personDetails.address, connection);
                    personDetails.setAddress(addressDetails);
                }

                recordList.add(personDetails);
            }

        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        return recordList;
    }

    private Person constructPerson(ResultSet resultSet) throws Exception {
        Person personDetails = new Person();
        Address address = new Address();

        personDetails.setId                 (resultSet.getLong(1));
        personDetails.setFirstName          (resultSet.getString(2));
        personDetails.setLastName           (resultSet.getString(3));
        personDetails.setEmail              (resultSet.getString(4));
        address.setId                       (resultSet.getLong(5));
        personDetails.setBirthDate          (resultSet.getDate(6));

        personDetails.setAddress(address);
        return personDetails;
    }

    public Person updatePerson(Person personDetails,
                               boolean includeAddress,
                               Connection connection) throws Exception {

        List<AppErrorCode> exceptionList = validatePerson(personDetails, true, connection);
        if (includeAddress == true) {
            try {
                Address address = addressService.updateAddress(personDetails.address, connection);
                personDetails.setAddress(address);
            } catch (AppException appExcp) {
                exceptionList.addAll(appExcp.getExceptionList());
            }
        }

        if (!exceptionList.isEmpty()) {
            throw new AppException(exceptionList);
        }

        String updateQuery = new StringBuilder("UPDATE jdbc_person SET ").append("first_name = ?, ")
                                                                         .append("last_name = ?, ")
                                                                         .append("email = ?, ")
                                                                         .append("birth_date = ?")
                                                                         .append(" WHERE ")
                                                                         .append("id = ?;")
                                                                         .toString();
        Person updatedPersonDetails;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, personDetails.firstName);
            updateStatement.setString(2, personDetails.lastName);
            updateStatement.setString(3, personDetails.email);
            updateStatement.setDate(4, personDetails.birthDate);
            updateStatement.setLong(5, personDetails.id);

            updateStatement.executeUpdate();
            updatedPersonDetails = readPerson(personDetails, true, connection);
            updateStatement.close();

        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        return updatedPersonDetails;
    }

    public Person deletePerson(Person personDetails, Connection connection) throws Exception {
        String deleteQuery = "DELETE FROM jdbc_person WHERE id = ?;";
        Person deletedPersonDetails;

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setLong(1, personDetails.id);

            deletedPersonDetails = readPerson(personDetails, true, connection);
            deleteStatement.executeUpdate();
            addressService.deleteAddress(deletedPersonDetails.address, connection);
            deleteStatement.close();

        } catch(SQLException dbError) {
            throw new AppException(AppErrorCode.ERROR_DATABASE, dbError);
        }

        return deletedPersonDetails;
    }

    public static void main(String[] args) throws Exception {
        PersonService personService = new PersonService();
        ConnectionSetup connect = new ConnectionSetup();
        Connection connection = connect.setConnection();
//        personService.insertPersonValue(new Person("Parallel", "Universe", "parallel@interDimension.com", 1l, Date.valueOf("2048-02-30")),
//                                        null, connection);
//     try {
//        Person temp = personService.createPerson(new Person("Parallel", "Universe", "parallel@interDimension.com", Date.valueOf("2048-02-29")),
//                new Address("X24", "Earth203", 998877), connection);
//    } catch(AppException e) {
//        System.out.println(e.getErrorMessage() + ": " + e.getCause());
//    }
//        Person tempPerson2 = new Person("Parallel", "Universe2", "parallel2@interDimension.com", Date.valueOf("2048-02-29"));
//        Address tempAddress2 = new Address("X24", "Earth203", 998877);
//        Person temp2 = personService.createPerson(tempPerson2,
//                tempAddress2, connection);

//                        Person tempPerson = new Person("Mohammed Almas", "Ansari", "almas@gmail.com", Date.valueOf("1997-06-02"));
//                Address addr = new Address();
//                addr.setId(1l);
//                tempPerson.setAddress(addr);
//                Person temp = personService.createPerson(tempPerson,
//                                        null, connection);

        Object value = "Parallel";
        Object anotherValue = "na";

//        Person atempPerson = new Person();
//        atempPerson.setId(4l);
//        try {
//        Person atemp = personService.readPerson(atempPerson , true, connection);
//        } catch(AppException e) {
//            System.out.println(e.getErrorMessage() + ": " + e.getCause());
//        }
//        System.out.println(atemp.toString());
//        System.out.println(temp2.toString());
//        personService.insertPersonValue(new Person("Mohammed Almas2", "Ansari2", "almas123@gmail.com", 2l, Date.valueOf("1997-06-02")),
//              null, connection);
//        Person personTwo = new Person("Raunak", "Chaudhary", "almas@gmail.com", Date.valueOf("1996-10-30"));
//        personTwo.setAddress(new Address("Sector 17", "Chandigarh", 220504));
//        try {
//        Person temp = personService.updatePersonValue(personTwo, false, connection);
//        System.out.println(temp.toString());
//        } catch (AppException err) {
//            List<AppException> exceptionList = err.getExceptionList();
//            exceptionList.forEach(System.out::println);
//        }
//        Person temp = personService.deletePersonValue("first_name", anotherValue, connection);
//        System.out.println(temp.toString());
//        List<Person> temp = personService.readAllPerson(false, connection);
//        temp.forEach(System.out::println);
//        Object value = "Parallel";
        String[] fields = {"first name"};
        List<Person> temp = personService.searchPerson(fields, "ha", connection, "id", "First Name", "Last name", "EMAIL", "birthday");
        for (Person person : temp) {
            System.out.println(person);
        }
        //        System.out.println(temp.id + "\t" + temp.firstName + "\t" + temp.lastName + "\t" + temp.email + "\t" + temp.createdDate);
    }
}


