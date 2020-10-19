package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable(){
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (id INT, name VARCHAR(20), lastName VARCHAR(20), age INT)";

        try(Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);

            System.out.println("Data was created");
        }catch (Exception ex){
            System.out.println("Error");
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE if exists users");
            System.out.println("Data was deleted");
        }catch (Exception ex){
            System.out.println("Error");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSql = "INSERT users (name, lastName, age)" +
                "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")";

        try (Connection conn = Util.getConnection()) {
            try (Statement statemnt = conn.createStatement()) {
                conn.setAutoCommit(false);
                statemnt.executeUpdate(saveSql);
                conn.commit();
                System.out.println("User " + name + "added to DBaseSaved");
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
            }
            conn.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE id =" + id);
            System.out.println("User was deleted");
        }catch (Exception ex){
            System.out.println("Error");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String allusers = "SELECT name, lastName, age FROM users";
        try(Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(allusers);
            while (result.next()){
                User user = new User();
                user.setName(result.getString(1));
                user.setLastName(result.getString(2));
                user.setAge(result.getByte(3));
                list.add(user);
            }
        }catch (Exception ex){
            System.out.println("Error");
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
            System.out.println("Users were deleted");
        }catch (Exception ex){
            System.out.println("Error");
        }

    }
}
