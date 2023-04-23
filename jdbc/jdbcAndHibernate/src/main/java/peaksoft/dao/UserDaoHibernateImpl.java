package peaksoft.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    EntityManagerFactory entityManagerFactory = Util.getEntityManager();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
    }

    @Override
    public void dropUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from users").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Table 'users' is dropped.");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removeUserById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(user.getName() + " is deleted!!!");

    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User>user = entityManager.createQuery("select u from users u ",User.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public void cleanUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from users ");
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
