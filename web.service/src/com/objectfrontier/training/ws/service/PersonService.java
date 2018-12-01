/**
 *
 */
package com.objectfrontier.training.ws.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.objectfrontier.training.ws.connection.ConnectionSetup;
import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;
import com.objectfrontier.training.ws.pojo.Address;
import com.objectfrontier.training.ws.pojo.Person;
import com.objectfrontier.training.ws.util.ServiceConstant;

/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
public class PersonService implements ServiceConstant {

    public PersonService() {}

    private AddressService addressService = new AddressService();
    public PersonService(AddressService addressService) {
        this.addressService = addressService;
    }

    private boolean isEmpty(String stringToBeChecked) {
        return Objects.isNull(stringToBeChecked) || stringToBeChecked.equals("");
    }

    private List<AppErrorCode> validatePerson(Person personDetails, boolean forUpdate, Connection connection) {

        List<AppErrorCode> exceptionList = new ArrayList<>();

        if (isEmpty(personDetails.getFirstName())) {
            exceptionList.add(AppErrorCode.ERROR_NULL_FIRST_NAME);
        }

        if (isEmpty(personDetails.getLastName())) {
            exceptionList.add(AppErrorCode.ERROR_NULL_LAST_NAME);
        }

        ResultSet nameCheckResultSet;
        try {
            if (forUpdate == true) {

                PreparedStatement nameCheck = connection.prepareStatement(PERSON_NAME_CHECK_QUERY_FOR_UPDATE);
                nameCheck.setString(1, personDetails.getFirstName());
                nameCheck.setString(2, personDetails.getLastName());
                nameCheck.setLong(3, personDetails.getId());
                nameCheckResultSet = nameCheck.executeQuery();

            } else {
                PreparedStatement nameCheck = connection.prepareStatement(PERSON_NAME_CHECK_QUERY);
                nameCheck.setString(1, personDetails.getFirstName());
                nameCheck.setString(2, personDetails.getLastName());
                nameCheckResultSet = nameCheck.executeQuery();
            }
            nameCheckResultSet.next();
            int duplicateNameCount = nameCheckResultSet.getInt(ID_COUNT);
            if (duplicateNameCount > 0) {
                exceptionList.add(AppErrorCode.ERROR_DUPL_NAME);
            }
        } catch(Exception createError) {
            throw new AppException(AppErrorCode.ERROR_CREATE_FAILED, createError);
        }

        if (isEmpty(personDetails.getEmail())) {
            exceptionList.add(AppErrorCode.ERROR_NULL_EMAIL);
        }

        ResultSet emailCheckResultSet;
        try {
            if (forUpdate == true) {
                PreparedStatement emailCheck = connection.prepareStatement(PERSON_EMAIL_CHECK_QUERY_FOR_UPDATE);
                emailCheck.setString(1, personDetails.getEmail());
                emailCheck.setLong(2, personDetails.getId());
                emailCheckResultSet = emailCheck.executeQuery();

            } else {

                PreparedStatement emailCheck = connection.prepareStatement(PERSON_EMAIL_CHECK_QUERY);
                emailCheck.setString(1, personDetails.getEmail());
                emailCheckResultSet = emailCheck.executeQuery();
            }
            emailCheckResultSet.next();
            int duplicateEmailCount = emailCheckResultSet.getInt(ID_COUNT);
            if (duplicateEmailCount > 0) {
                exceptionList.add(AppErrorCode.ERROR_DUPL_EMAIL);
            }
        } catch(Exception dbError) {
            throw new AppException(AppErrorCode.ERROR_CREATE_FAILED, dbError);
        }

        if (Objects.isNull(personDetails.getBirthDate())) {
            exceptionList.add(AppErrorCode.ERROR_NULL_BIRTH_DATE);
        }

        return exceptionList;
    }

    public Person createPerson(Person personDetails) {

        Connection connection = ConnectionSetup.connectionThread.get();
        List<AppErrorCode> exceptionList = validatePerson(personDetails, false, connection);
            try {
                addressService.createAddress(personDetails.getAddress());
            } catch (AppException appExcp) {
                exceptionList.addAll(appExcp.getExceptionList());
            }

        if(!exceptionList.isEmpty()) {
            throw new AppException(exceptionList);
        }

        try {
            PreparedStatement insertStatement =  connection.prepareStatement(INSERT_PERSON_QUERY, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString    (1, personDetails.getFirstName());
            insertStatement.setString    (2, personDetails.getLastName());
            insertStatement.setString    (3, personDetails.getEmail());
            insertStatement.setLong      (4, personDetails.getAddress().getId());
            insertStatement.setDate      (5, personDetails.getBirthDate());
            insertStatement.setBoolean   (6, personDetails.isAdmin());
            insertStatement.setString    (7, personDetails.getPassword());
            insertStatement.executeUpdate();

            ResultSet autoGeneratedId = insertStatement.getGeneratedKeys();
            autoGeneratedId.next();
            personDetails.setId(autoGeneratedId.getLong(1));

            if (personDetails.getId() == 0) {
                throw new AppException(AppErrorCode.ERROR_NOT_AUTO_GEN_PERSON_ID);
            }
            insertStatement.close();

        } catch(Exception createError) {
            throw new AppException(AppErrorCode.ERROR_CREATE_FAILED, createError);
        }

        return personDetails;
    }

    public List<Person> searchPerson(String[] searchField,
                                     String searchKeyword,
                                     String ...resultSetColumn) {

        String[] databaseColumn = new String[resultSetColumn.length];
        int index = 0;

        for (String resultColumn : resultSetColumn) {
            if (resultColumn.equalsIgnoreCase("id")) databaseColumn[index] = PERSON_ID;
            if (resultColumn.equalsIgnoreCase("first name")) databaseColumn[index] = FIRST_NAME;
            if (resultColumn.equalsIgnoreCase("last name")) databaseColumn[index] = LAST_NAME;
            if (resultColumn.equalsIgnoreCase("email")) databaseColumn[index] = EMAIL;
            if (resultColumn.equalsIgnoreCase("birthday")) databaseColumn[index] = BIRTH_DATE;
            if (resultColumn.equalsIgnoreCase("created date")) databaseColumn[index] = CREATED_DATE;
            if (resultColumn.equalsIgnoreCase("address id")) databaseColumn[index] = ADDRESS_ID;
            if (resultColumn.equalsIgnoreCase("street")) databaseColumn[index] = STREET;
            if (resultColumn.equalsIgnoreCase("city")) databaseColumn[index] = CITY;
            if (resultColumn.equalsIgnoreCase("pin code")) databaseColumn[index] = POSTAL_CODE;
            index++;
        }

        String column = Arrays.toString(databaseColumn);
        String resultantColumn = column.substring(1, column.length() - 1);

        String[] searchFieldArray = new String[searchField.length];
        int searchFieldIndex = 0;
        for (String searchFieldName : searchField) {
            if (searchFieldName.equalsIgnoreCase("id")) searchFieldArray[searchFieldIndex] = PERSON_ID;
            if (searchFieldName.equalsIgnoreCase("first name")) searchFieldArray[searchFieldIndex] = FIRST_NAME;
            if (searchFieldName.equalsIgnoreCase("last name")) searchFieldArray[searchFieldIndex] = LAST_NAME;
            if (searchFieldName.equalsIgnoreCase("email")) searchFieldArray[searchFieldIndex] = EMAIL;
            if (searchFieldName.equalsIgnoreCase("birthday")) searchFieldArray[searchFieldIndex] = BIRTH_DATE;
            searchFieldIndex++;
        }

        String searchQuery = String.format(SEARCH_PERSON_QUERY_FORMAT,
                                           resultantColumn,
                                           constructQuery(searchFieldArray));

        Connection connection = ConnectionSetup.connectionThread.get();
        List<Person> resultList = new ArrayList<>();

        try {
            PreparedStatement searchStatement = connection.prepareStatement(searchQuery);

            for (int fieldIndex = 1; fieldIndex <= searchFieldArray.length; fieldIndex++) {
                searchStatement.setString(fieldIndex,"%" + searchKeyword + "%");
            }
            ResultSet searchResult = searchStatement.executeQuery();

            if (searchResult.next() == true) {
                searchResult.beforeFirst();
                while (searchResult.next()) {
                    Person personDetails = new Person();
                    Address addressDetails = new Address();

                    for(String columnName : databaseColumn) {
                        if (columnName.equalsIgnoreCase(PERSON_ID)) {
                            personDetails.setId(searchResult.getLong(columnName));
                        }
                        if (columnName.equalsIgnoreCase(FIRST_NAME)) {
                            personDetails.setFirstName(searchResult.getString(columnName));
                        }
                        if (columnName.equalsIgnoreCase(LAST_NAME)) {
                            personDetails.setLastName(searchResult.getString(columnName));
                        }
                        if (columnName.equalsIgnoreCase(EMAIL)) {
                            personDetails.setEmail(searchResult.getString(columnName));
                        }
                        if (columnName.equalsIgnoreCase(BIRTH_DATE)) {
                            personDetails.setBirthDate(searchResult.getDate(columnName));
                        }
                        if (columnName.equalsIgnoreCase(CREATED_DATE)) {
                            personDetails.setCreatedDate(searchResult.getTimestamp(columnName));
                        }
                        if (columnName.equalsIgnoreCase(ADDRESS_ID)) {
                            addressDetails.setId(searchResult.getLong(columnName));
                        }
                        if (columnName.equalsIgnoreCase(STREET)) {
                            addressDetails.setStreet(searchResult.getString(columnName));
                        }
                        if (columnName.equalsIgnoreCase(CITY)) {
                            addressDetails.setCity(searchResult.getString(columnName));
                        }
                        if (columnName.equalsIgnoreCase(POSTAL_CODE)) {
                            addressDetails.setPostalCode(searchResult.getInt(columnName));
                        }
                    }
                    personDetails.setAddress(addressDetails);
                    resultList.add(personDetails);
                }
                searchResult.close();
                searchStatement.close();
            } else {
                throw new AppException(AppErrorCode.ERROR_NO_RECORD_FOUND);
            }

        } catch(Exception searchError) {
            if (searchError instanceof AppException) {
                AppException appExcp = (AppException) searchError;
                throw new AppException(appExcp.getExceptionList());
            }
            throw new AppException(AppErrorCode.ERROR_SEARCH_FAILED, searchError);
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

    public Person readPerson(Person personId, boolean includeAddress) {

        Connection connection = ConnectionSetup.connectionThread.get();
        Person personDetails = new Person();

        try {
            PreparedStatement readStatement = connection.prepareStatement(READ_PERSON_QUERY);
            readStatement.setLong(1, personId.getId());
            ResultSet readPersonResultSet = readStatement.executeQuery();

            if(readPersonResultSet.next()) {
                personDetails = constructPerson(readPersonResultSet);
                if (includeAddress == true) {
                    Address addressDetails = addressService.readAddress(personDetails.getAddress());
                    personDetails.setAddress(addressDetails);
                }
                readPersonResultSet.close();
                readStatement.close();
            } else {
                throw new AppException(AppErrorCode.ERROR_NO_RECORD_FOUND);
            }

        } catch(Exception readError) {
            if (readError instanceof AppException) {
                AppException appExcp = (AppException) readError;
                throw new AppException(appExcp.getExceptionList());
            }
            throw new AppException(AppErrorCode.ERROR_READ_FAILED, readError);
        }

        return personDetails ;
    }

    public List<Person> readAllPerson(boolean includeAddress) {

        Connection connection = ConnectionSetup.connectionThread.get();
        List<Person> recordList = new ArrayList<>();

        try {
            PreparedStatement readAllStatement = connection.prepareStatement(READ_ALL_PERSON_QUERY);
            ResultSet readAllPersonResultSet = readAllStatement.executeQuery();

            if (readAllPersonResultSet.next() == true) {
                readAllPersonResultSet.beforeFirst();
                while (readAllPersonResultSet.next()) {
                    Person personDetails = constructPerson(readAllPersonResultSet);

                    if (includeAddress == true) {
                        Address addressDetails = addressService.readAddress(personDetails.getAddress());
                        personDetails.setAddress(addressDetails);
                    }

                    recordList.add(personDetails);
                }
                readAllPersonResultSet.close();
                readAllStatement.close();
            } else {
                throw new AppException(AppErrorCode.ERROR_NO_RECORD_FOUND);
            }

        } catch(Exception readAllError) {
            if (readAllError instanceof AppException) {
                AppException appExcp = (AppException) readAllError;
                throw new AppException(appExcp.getExceptionList());
            }
            throw new AppException(AppErrorCode.ERROR_READ_ALL_FAILED, readAllError);
        }

        return recordList;
    }

    private Person constructPerson(ResultSet resultSet) {
        Person personDetails = new Person();
        Address address = new Address();

        try {
            personDetails.setId                 (resultSet.getLong(ID));
            personDetails.setFirstName          (resultSet.getString(FIRST_NAME));
            personDetails.setLastName           (resultSet.getString(LAST_NAME));
            personDetails.setEmail              (resultSet.getString(EMAIL));
            address.setId                       (resultSet.getLong(ADDRESS_ID));
            personDetails.setBirthDate          (resultSet.getDate(BIRTH_DATE));
            personDetails.setAdmin              (resultSet.getBoolean(IS_ADMIN));

            personDetails.setAddress(address);
            return personDetails;

        } catch (Exception constructError){
            throw new AppException(constructError);
        }
    }

    public Person updatePerson(Person personDetails, boolean includeAddress) {

        Connection connection = ConnectionSetup.connectionThread.get();
        List<AppErrorCode> exceptionList = validatePerson(personDetails, true, connection);
        if (includeAddress == true) {
            try {
                Address address = addressService.updateAddress(personDetails.getAddress());
                personDetails.setAddress(address);
            } catch (AppException appExcp) {
                exceptionList.addAll(appExcp.getExceptionList());
            }
        }

        if (!exceptionList.isEmpty()) {
            throw new AppException(exceptionList);
        }

        Person updatedPersonDetails;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PERSON_QUERY);
            updateStatement.setString(1, personDetails.getFirstName());
            updateStatement.setString(2, personDetails.getLastName());
            updateStatement.setString(3, personDetails.getEmail());
            updateStatement.setDate(4, personDetails.getBirthDate());
            updateStatement.setLong(5, personDetails.getId());

            updateStatement.executeUpdate();
            updatedPersonDetails = readPerson(personDetails, true);
            updateStatement.close();

        } catch(Exception updateError) {
            throw new AppException(AppErrorCode.ERROR_UPDATE_FAILED, updateError);
        }

        return updatedPersonDetails;
    }

    public Person deletePerson(Person personDetails) {
        Person deletedPersonDetails;

        try {
            Connection connection = ConnectionSetup.connectionThread.get();
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_PERSON_QUERY);
            deleteStatement.setLong(1, personDetails.getId());

            deletedPersonDetails = readPerson(personDetails, true);
            deleteStatement.executeUpdate();
            addressService.deleteAddress(deletedPersonDetails.getAddress());
            deleteStatement.close();

        } catch(Exception deleteError) {
            throw new AppException(AppErrorCode.ERROR_DELETE_FAILED, deleteError);
        }

        return deletedPersonDetails;
    }

}


