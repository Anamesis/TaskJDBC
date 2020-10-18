package jm.task.core.jdbc.util;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Util {
    private String URL =
    public static void main (String[] args){
        Connection connection;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public static Connection getConnection() throws SQLException {
//        System.out.println("nn***** MySQL JDBC Connection Testing *****");
//        Connection conn = null;
//        try {
//            Class.forName("com.MySQL.jdbc.Driver").newInstance();
//            String userName = "root";
//            String password = "123";
//            String url = "jdbc:MySQL://localhost:3306/Users1";
//            conn = DriverManager.getConnection(url, userName, password);
//            System.out.println("Database Connection Established...");
//        } catch (Exception ex) {
//            System.err.println("Cannot connect to database server");
//            ex.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    System.out.println("n***** Let terminate the Connection *****");
//                    conn.close();
//                    System.out.println("Database connection terminated... ");
//                } catch (Exception ex) {
//                    System.out.println("Error in connection termination!");
//                }
//            }
//        }
//        return conn;
//    }
}