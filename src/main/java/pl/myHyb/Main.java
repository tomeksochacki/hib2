package pl.myHyb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class Main {
    static SessionFactory sessionFactory;
    public static void main(String[] args) {
    sessionFactory = new Configuration().configure().buildSessionFactory();
    saveUser(new User(0, "tomek", "tomek"));
    saveUser(new User(0,"tomek2", "tomek2"));
    saveUser(new User(0,"tomek3", "tomek3"));

    //updateUser(new User(1,"romek", "romek"));
    //deleteUser(new User(1, "Domek", "Domek"));
    //persistUser(new User(1, "Julek", "Julek"));
    }

    public static void saveUser(User user){
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

    public static void updateUser(User user){
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

    public static void deleteUser(User user){
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        }catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

    public static void persistUser(User user){
        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        }catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

   /* public static List<User> getAllUsers(){
        Session session = sessionFactory.openSession();

    }*/
}
