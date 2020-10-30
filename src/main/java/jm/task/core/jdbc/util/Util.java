package jm.task.core.jdbc.util;

import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;


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
    public static SessionFactory sessionFactory;
    private Util(){}


    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/Users1")
                .setProperty("hibernate.connection.password", "123")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.pool_size", "2")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                .addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return sessionFactory = configuration.buildSessionFactory(sBuilder.build());
    }
    }
