package User.CRUD.dao;

import User.CRUD.model.User;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext                 //(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public List <User> getAllUsers() {
        return em.createQuery("SELECT DISTINCT u FROM User u JOIN FETCH u.roles", User.class).getResultList();
    }
    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void remove(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public User getById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByName(String userName) {
        return em
                .createQuery("select u from User u JOIN FETCH u.roles where u.firstName = :username", User.class)
                .setParameter("username", userName)
                .getSingleResult();
    }
}