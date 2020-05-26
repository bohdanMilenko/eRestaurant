package com.application.repository;

import com.application.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class UserRepoImpl implements IUserRepo {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User findUserById(int id) {
        return em.find(User.class,id);
    }

    @Override
    public Collection<User> findUserByName(String name) {

        return null;
    }

    @Override
    public Collection<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return null;
    }

    @Override
    public void save(User user) {

    }
}
