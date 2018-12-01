package com.objectfrontier.training.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CollegeDataFetch {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://pc1620:3306/mohammed_mohammed?useSSL=false";
        String username = "mohammed_mohammed";
        String password = "demo";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM edu_college";
        ResultSet result = statement.executeQuery(sql);

        while(result.next()) {
            int id = result.getInt(1);
            String college_code = result.getString(2);
            String college_name = result.getString(3);
            String univ_code = result.getString(4);
            String city = result.getString(5);
            String state = result.getString(6);

            System.out.println(id + "\t" +
                               college_code + "\t" +
                               college_name + "\t" +
                               univ_code + "\t" +
                               city + "\t" +
                               state);
        }

        result.close();
        statement.close();
        connection.close();
    }
}
