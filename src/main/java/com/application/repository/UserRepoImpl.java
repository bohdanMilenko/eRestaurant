package com.application.repository;

import com.application.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepoImpl implements IUserRepo {

    @PersistenceContext
    EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(IUserRepo.class);

    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUserByNameLike(String name) {

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        logger.info("getting all users from DB", query.getResultList());
        logger.debug("getting all users from DB", query.getResultList());
        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUserByUserRole(int userRoleId) {
        return null;
    }
}
