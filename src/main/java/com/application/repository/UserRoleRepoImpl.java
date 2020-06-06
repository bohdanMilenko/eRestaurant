package com.application.repository;

import com.application.entity.UserRole;
import com.application.exception.RepoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRoleRepoImpl implements IUserRoleRepo {
    @PersistenceContext
    EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(UserRoleRepoImpl.class);

    @Override
    public void addRole(UserRole role) throws RepoException {
        try {
            em.persist(role);
        } catch (PersistenceException e) {
            logger.error("Violation of UserRepo table constraints: addRole(role={}) caused: {}", role, e.toString());
            throw new RepoException("Such Entity already exists in DB: " + role.getRoleName(), e);
        }
    }


    @Override
    public List<UserRole> getAllRoles() {
        TypedQuery<UserRole> allUserRoles = em.createQuery("SELECT ur FROM UserRole ur", UserRole.class);
        return allUserRoles.getResultList();
    }

    @Override
    public UserRole getByRoleName(String roleName) throws RepoException {
        try {
            TypedQuery<UserRole> query = em.createQuery("SELECT u FROM UserRole u WHERE u.roleName = :roleName", UserRole.class);
            query.setParameter("roleName", roleName);
            logger.info("Found record: " + query.getSingleResult().toString(), query.getSingleResult());
            return query.getSingleResult();
        } catch (NonUniqueResultException e) {
            logger.error("Multiple results found for passed : {}, cause: {}", roleName, e.toString());
            return null;
        } catch (NoResultException e) {
            logger.error("Unable to find such record in table UserName {}, cause: {}", roleName, e.toString());
            return null;
        }
    }


    @Override
    public List<UserRole> getByUserRoleNameLike(String roleName) throws RepoException {
        TypedQuery<UserRole> query = em.createQuery("SELECT u FROM UserRole u WHERE u.roleName LIKE :roleName", UserRole.class);
        query.setParameter("roleName", roleName + "%");
        return query.getResultList();

    }


    @Override
    public boolean updateName(UserRole role, UserRole newUserRole) throws RepoException {
        logger.info("Entering updateName( old = {}, new = {} )", role, newUserRole);
        UserRole oldUserRole = getByRoleName(role.getRoleName());
        oldUserRole.setRoleName(newUserRole.getRoleName());
        logger.info("Successfully updated roleNames old = {}, new = {}", role, newUserRole);
        return true;

    }


    @Override
    @Transactional
    public boolean remove(UserRole role) throws RepoException {
        UserRole userRole = getByRoleName(role.getRoleName());
        Query query = em.createQuery("DELETE FROM UserRole ur where ur.roleName IN :roleName");
        query.setParameter("roleName", userRole.getRoleName());
        logger.info("Removed from DB userRole = {})", role);
        query.executeUpdate();
        return true;


    }
}
