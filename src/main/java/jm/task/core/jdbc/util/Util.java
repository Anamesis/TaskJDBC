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


        public static SessionFactory getSessionFactory () {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration().configure();
                    configuration.addAnnotatedClass(User.class);
                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    sessionFactory = configuration.buildSessionFactory(builder.build());

                } catch (Exception e) {
                    System.out.println("Исключение!" + e);
                }
            }
            return sessionFactory;
        }
    }
