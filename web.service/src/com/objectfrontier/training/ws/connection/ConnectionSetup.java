/**
 *
 */
package com.objectfrontier.training.ws.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
public class ConnectionSetup {

    private static HikariDataSource ds;
    public static ThreadLocal<Connection> connectionThread = new ThreadLocal<>();

    static {
        InputStream inStream = ConnectionSetup.class.getResourceAsStream("dbConfiguration.properties");
        Properties configProperties = new Properties();

        try {
            configProperties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig(configProperties);
        ds = new HikariDataSource(config);
    }

    public static void setConnection() {
        try {
            Connection connection = ds.getConnection();
            connectionThread.set(connection);
        } catch (Exception conError) {
            throw new AppException(AppErrorCode.ERROR_CONNECTION_FAILED, conError);
        }
    }

    public static void releaseConnection(boolean doCommit) {

        try {
            Connection connection = connectionThread.get();
            if (doCommit) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
            connectionThread.remove();

        } catch (Exception conError) {
            throw new AppException(AppErrorCode.ERROR_CONNECTION_FAILED, conError);
        }
    }

}

