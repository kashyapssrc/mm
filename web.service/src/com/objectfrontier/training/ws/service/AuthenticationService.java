/**
 *
 */
package com.objectfrontier.training.ws.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.objectfrontier.training.ws.connection.ConnectionSetup;
import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;
import com.objectfrontier.training.ws.pojo.Person;
import com.objectfrontier.training.ws.util.ServiceConstant;

/**
 * @author mohammed.mohammed
 * @since  Nov 20, 2018
 */
public class AuthenticationService implements ServiceConstant {

    public Person authenticateUser(String userName, String password) {

        Connection connection = ConnectionSetup.connectionThread.get();
        try {
            PreparedStatement readAdminStatement = connection.prepareStatement(FIND_USER_QUERY);
            readAdminStatement.setString(1, userName);
            readAdminStatement.setString(2, password);
            ResultSet resultSet = readAdminStatement.executeQuery();

            PersonService personService = new PersonService();

            if(resultSet.next()) {
                Person userId = new Person();
                userId.setId(resultSet.getLong("id"));
                Person userDetails = personService.readPerson(userId, true);
                return userDetails;

            } else {
                throw new AppException(AppErrorCode.ERROR_INVALID_USER);
            }

        } catch (Exception userError){
            if (userError instanceof AppException) {
                AppException appExcp = (AppException) userError;
                throw new AppException(appExcp.getExceptionList());
            }
            throw new AppException(AppErrorCode.ERROR_AUTHENTICATION_FAILED, userError);
        }
    }

}
