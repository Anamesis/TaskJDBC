package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.security.auth.login.Configuration;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session = Util.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id INT, name VARCHAR(20)," +
                    " lastName VARCHAR(20), age INT)");
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) {
                tx1.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Query query = session.createSQLQuery("DROP TABLE if exists users");
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) {
                tx1.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session.save(new User(name, lastName, age));
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) {
                tx1.rollback();
            }
        }finally {
            session.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx1.commit();
        }catch(Exception e) {
            if (tx1 != null) {
                tx1.rollback();
            }
        }finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = session.createQuery("FROM User").list();
        return user;
    }


    @Override
    public void cleanUsersTable() {
        try {
            Query query = session.createSQLQuery("DELETE FROM users");
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) {
                tx1.rollback();
            }
        }finally {
            session.close();
        }
    }
}
