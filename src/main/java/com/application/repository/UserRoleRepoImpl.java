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
import java.util.List;

import static com.application.util.PassedEntitiesValidator.*;

@Repository
public class UserRoleRepoImpl implements IUserRoleRepo {
    @PersistenceContext
    EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(UserRoleRepoImpl.class);

    @Override
    public void addRole(UserRole role) throws RepoException {
        try {
            logger.info("Starting writing to DB, addRole( role = {})", role);
            validateObjectsForNull(role);
            validateUserRoleFieldsForNulls(role);
                em.persist(role);
        }catch (EntityValidationException e){
            logger.debug("Object failed validation for add(province = {}))", role);
            throw new RepoException("Invalid passed argument. Unable to add new UserRole "+ role, e);
        }catch (PersistenceException e){
            logger.debug("Violation of UserRepo table constraints: addRole(role={}) caused: {}", role, e.toString());
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
                validateObjectsForNull(roleName);
                TypedQuery<UserRole> query = em.createQuery("SELECT u FROM UserRole u WHERE u.roleName = :roleName", UserRole.class);
                query.setParameter("roleName", roleName);
                logger.info("Found record: " +query.getSingleResult().toString(), query.getSingleResult() );
                return query.getSingleResult();
            }catch (EntityValidationException e) {
                logger.debug("Passed argument failed validation getByRoleName( roleName = {}), cause: {}",roleName, e);
                throw new RepoException("Failed validation",e);
            }
            catch (NonUniqueResultException e){
                logger.debug("Multiple results found for passed : {}, cause: {}",roleName, e.toString());
                return null;
            }catch (NoResultException e){
                logger.debug("Unable to find such record in table UserName {}, cause: {}",roleName, e.toString());
                return null;
            }
        }


    @Override
    public List<UserRole> getByUserRoleNameLike(String roleName) throws RepoException {
        try {
            validateObjectsForNull(roleName);
            TypedQuery<UserRole> query = em.createQuery("SELECT u FROM UserRole u WHERE u.roleName LIKE :roleName", UserRole.class);
            query.setParameter("roleName", roleName +"%");
            return query.getResultList();
        }
        catch (EntityValidationException e) {
            logger.debug("Passed argument failed validation getByRoleName( roleName = {}), cause: {}",roleName, e);
            throw new RepoException("Failed validation",e);
        }
    }


    @Override
    public boolean updateName(UserRole role, UserRole newUserRole) throws RepoException {
        try {
            logger.info("Entering updateName( old = {}, new = {} )", role, newUserRole);
            validateObjectsForNull(role, newUserRole);
            validateUserRoleFieldsForNulls(role, newUserRole);
            UserRole oldUserRole = getByRoleName(role.getRoleName());
            oldUserRole.setRoleName(newUserRole.getRoleName());
            logger.info("Successfully updated roleNames old = {}, new = {}", role, newUserRole);
            return true;
        }catch (EntityValidationException e){
            //TODO - Logging
            //What level of logging do I need when catching exception?
            logger.debug("Failed validation while updating UserRoles: old = {}, new = {}, cause: {}", role, newUserRole, e.toString());
            throw new RepoException(e);
        }
    }


    @Override
    @Transactional
    public boolean remove(UserRole role) throws RepoException {
        try {
            logger.info("Entering remove( userRole = {})", role);
            validateObjectsForNull(role);
            validateUserRoleFieldsForNulls(role);
            UserRole userRole = getByRoleName(role.getRoleName());
            Query query = em.createQuery("DELETE FROM UserRole ur where ur.roleName IN :roleName");
            query.setParameter("roleName", userRole.getRoleName());
            logger.info("Removed from DB userRole = {})", role);
            query.executeUpdate();
            return true;
        }catch (EntityValidationException e){
            logger.debug("{} failed validation for nulls", role);
            throw new RepoException(e);
        }
    }
}
