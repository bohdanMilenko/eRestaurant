package com.application.repository;

import com.application.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class UserRepoImpl implements IUserRepo {

    @PersistenceContext
    EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(IUserRepo.class);

    @Override
    public User findUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public Collection<User> findUserByName(String name) {

        return null;
    }

    @Override
    @Transactional
    public Collection<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        logger.info("getting all users from DB", query.getResultList());
        logger.debug("getting all users from DB", query.getResultList());
        Collection<User> users = query.getResultList();

        return users;
    }

    @Override
    public void save(User user) {

    }
}
