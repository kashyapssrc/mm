package com.objectfrontier.training.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSetup {
    public Connection setConnection() throws Exception {
        String url = "jdbc:mysql://pc1620:3306/mohammed_mohammed?useSSL=false";
        String username = "mohammed_mohammed";
        String password = "demo";

        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
