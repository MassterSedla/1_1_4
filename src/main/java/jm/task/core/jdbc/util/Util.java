package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/db_kata_1_1_4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Koroleva1!";
    private static Connection connection;

    public static void connection() {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Not connection");
        }
    }

    public static PreparedStatement createStatement(String execute) {
        try {
            return connection.prepareStatement(execute);
        } catch (SQLException e) {
            System.out.println("Not connection");
            throw new RuntimeException(e);
        }
    }

    public static void close(){
        try{
            connection.close();
        } catch (SQLException e) {
            System.out.println("Not connection");
        }
    }
}
