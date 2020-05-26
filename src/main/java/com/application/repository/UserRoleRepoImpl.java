package com.application.repository;

import com.application.entity.User;
import com.application.entity.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class UserRoleRepoImpl implements IUserRoleRepo {
    @PersistenceContext
    EntityManager em;

    @Override
    public void addRole(UserRole role) {
        em.persist(role);
    }

    @Override
    public boolean remove(UserRole role) {
        if (role!=null){
            em.remove(role);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(UserRole role) {

        return true;
    }

    @Override
    public Collection<UserRole> getUserRole(String roleName) {
        TypedQuery<UserRole> query = em.createQuery("SELECT ur FROM UserRole ur WHERE ur.roleName like 'roleName'", UserRole.class);
        return query.getResultList();
    }

    @Override
    public Collection<UserRole> getAllRoles() {
        return null;
    }
}
