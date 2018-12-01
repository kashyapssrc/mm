/**
 *
 */
package com.objectfrontier.training.java.jdbc.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author mohammed.mohammed
 * @since  Oct 6, 2018
 */
public class ConnectionSetup {
    public Connection setConnection() throws Exception {
        String url = "jdbc:mysql://pc1620:3306/mohammed_mohammed?useSSL=false";

        InputStream inStream = ConnectionSetup.class.getResourceAsStream("dbConfiguration.properties");
        Properties configProperties = new Properties();
        configProperties.load(inStream);

        Connection connection = DriverManager.getConnection(url, configProperties);
        return connection;
    }
}

