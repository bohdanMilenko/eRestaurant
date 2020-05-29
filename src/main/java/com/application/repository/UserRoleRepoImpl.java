package com.application.repository;

import com.application.entity.UserRole;
import com.application.exception.DuplicatedEntityInDbException;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import static com.application.util.PassedEntitiesValidator.*;

@Repository
public class UserRoleRepoImpl implements IUserRoleRepo {
    @PersistenceContext
    EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(UserRoleRepoImpl.class);

    @Override
    public void addRole(UserRole role) throws RepoException {
        try {
            validateObjectsForNull(role);
            validateUserRoleFieldsForNulls(role);
            if (findByRoleName(role.getRoleName()) == null) {
                em.persist(role);
            }else {
                throw new DuplicatedEntityInDbException("Such Entity already exists in DB: " + role.getRoleName());
            }
        }catch (EntityValidationException e){
            throw new RepoException("Invalid passed argument. Unable to add new UserRole "+ role, e);
        }catch (DuplicatedEntityInDbException e){
            throw new RepoException("Such Entity already exists in DB: " + role.getRoleName(), e);
        }
    }

    @Override
    public UserRole findByRoleName(String roleName) throws RepoException {
            try {
                validateObjectsForNull(roleName);
                TypedQuery<UserRole> query = em.createQuery("SELECT u FROM UserRole u WHERE u.roleName = :roleName", UserRole.class);
                query.setParameter("roleName", roleName);
                logger.info("Found record: " +query.getSingleResult().toString(), query.getSingleResult() );
                return query.getSingleResult();
            }catch (EntityValidationException e) {
                logger.info("Passed argument failed validation: "+ roleName, e);
                throw new RepoException("Failed validation",e);
            }
            catch (NonUniqueResultException e){
                logger.info("Requested UserRole has multiple entities in DB: " + roleName, e);
                return null;
            }catch (NoResultException e){
                logger.info("Requested UserRole has no such entities in DB: " + roleName, e);
                return null;
            }
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
    @Transactional
    public boolean remove(UserRole role) throws RepoException {
        if (role!=null){
            UserRole userRole = findByRoleName(role.getRoleName());
            logger.info("Removing UserRole: " + userRole.toString());
            Query query = em.createQuery("DELETE FROM UserRole ur where ur.roleName IN :roleName");
            query.setParameter("roleName", userRole.getRoleName());
            query.executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateName(UserRole role, UserRole newUserRole) throws RepoException {
        try {
            validateObjectsForNull(role);
            validateObjectsForNull(newUserRole);
            validateUserRoleFieldsForNulls(role);
            validateUserRoleFieldsForNulls(newUserRole);
            UserRole oldUserRole = findByRoleName(role.getRoleName());
            oldUserRole.setRoleName(newUserRole.getRoleName());
        }catch (EntityValidationException e){
            //TODO - Logging
            //What level of logging do I need when catchig exception?
            logger.debug("Failed validation while updating oldUserRole: " + role.toString()
                    + " newUserRole " + newUserRole.toString(), e);
            throw new RepoException("Failed validation while updating oldUserRole: " + role.toString()
                    + " newUserRole " + newUserRole.toString(),e);
        }
        return true;
    }

    @Override
    public Collection<UserRole> getUserRole(String roleName) {
        TypedQuery<UserRole> query = em.createQuery("SELECT ur FROM UserRole ur WHERE ur.roleName like 'roleName'", UserRole.class);
        return query.getResultList();
    }

    @Override
    public Collection<UserRole> getAllRoles() {
        TypedQuery<UserRole> allUserRoles = em.createQuery("SELECT ur FROM UserRole ur", UserRole.class);
        return allUserRoles.getResultList();
    }
}
