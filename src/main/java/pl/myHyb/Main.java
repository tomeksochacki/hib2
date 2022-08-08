package pl.myHyb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;


public class Main {
    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        saveUser(new User(0, "tomek", "tomek"));
        saveUser(new User(0, "tomek2", "tomek2"));
        saveUser(new User(0, "tomek3", "tomek3"));

        //updateUser(new User(1,"romek", "romek"));
        //deleteUser(new User(1, "Domek", "Domek"));
        //persistUser(new User(1, "Julek", "Julek"));
        //List<User> users = getAllUsers();
        //System.out.println(users);
        //User user = getUserById(6);
        //System.out.println(user);

        Order order = new Order(0, new User(1, "tomek", "tomek"), 50);
        persistOrder(order);
    }

    public static void saveUser(User user) {
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateUser(User user) {
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteUser(User user) {
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void persistUser(User user) {
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.myHyb.User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    public static User getUserById(int id) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.myHyb.User WHERE id = :id");
        query.setParameter("id", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
        } finally {
            session.close();
        }
        return user;
    }

    public static void persistOrder(Order order) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
