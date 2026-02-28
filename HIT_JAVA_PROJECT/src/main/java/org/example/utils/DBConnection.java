package org.example.utils;

import java.sql.*;

public class DBConnection {
    private static String URL = "jdbc:mysql://localhost:3306/project_database";
    private static String USERNAME = "root";
    private static String PASSWORD = "Mhun@1311";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
