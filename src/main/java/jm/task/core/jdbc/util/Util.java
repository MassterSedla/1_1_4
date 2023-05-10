package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД Hibernate
    private static final String URL = "jdbc:mysql://localhost:3306/db_kata_1_1_4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Koroleva1!";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.setProperty(Environment.DRIVER, DRIVER);
                settings.setProperty(Environment.URL, URL);
                settings.setProperty(Environment.USER, USERNAME);
                settings.setProperty(Environment.PASS, PASSWORD);
                settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.setProperty(Environment.SHOW_SQL, "true");
                settings.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.setProperty(Environment.HBM2DDL_AUTO, "");

                sessionFactory = new Configuration().addProperties(settings).addAnnotatedClass(User.class).buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

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
