package com.application.repository;

import com.application.entity.UserRole;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import static com.application.util.PassedEntitiesValidator.*;

@Repository
public class UserRoleRepoImpl implements IUserRoleRepo {
    @PersistenceContext
    EntityManager em;

    @Override
    public void addRole(UserRole role) throws RepoException {
        try {
            validateObjectsForNull(role);
            validateUserRoleFieldsForNulls(role);
            if (findByRoleName(role.getRoleName()) == null)
                em.persist(role);
        }catch (EntityValidationException e){
            throw new RepoException("Invalid passed argument. Unable to add new UserRole "+ role, e);
        }
    }

    @Override
    public UserRole findByRoleName(String roleName) {
        if (roleName != null){
            TypedQuery<UserRole> query = em.createQuery("SELECT u FROM UserRole u WHERE u.roleName = :roleName", UserRole.class);
            query.setParameter("roleName", roleName);
            try {
                return query.getSingleResult();
            }catch (NoResultException e){
                return null;
            }
        }
        return null;
    }

    @Override
    public Collection<UserRole> findByUserRoleNameLike(String roleName) {
        if (roleName != null) {
            TypedQuery<UserRole> query = em.createQuery("SELECT u FROM UserRole u WHERE u.roleName LIKE :roleName", UserRole.class);
            query.setParameter("roleName", roleName +"%");
            return query.getResultList();
        }
        return null;
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
