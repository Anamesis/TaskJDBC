package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String userName = "root";
            String password = "123";
            String url = "jdbc:mysql://localhost:3306/Users1";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection..");
        } catch (Exception e) {
            System.out.println("Connection exception");
            e.printStackTrace();
        }
        return connection;
    }
}