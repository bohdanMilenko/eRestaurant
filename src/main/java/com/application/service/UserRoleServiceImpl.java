package com.application.service;

import com.application.entity.UserRole;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import com.application.exception.ServiceException;
import com.application.repository.IUserRoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.PassedEntitiesValidator.validateUserRoleFieldsForNulls;

@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    IUserRoleRepo userRoleRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    public UserRoleServiceImpl() {
    }

    public UserRoleServiceImpl(IUserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    @Transactional
    public void add(UserRole userRole) throws ServiceException {
        logger.info("Starting writing to DB, add( userRole = {})", userRole);
        try {
            validateObjectsForNull(userRole);
            validateUserRoleFieldsForNulls(userRole);
            userRoleRepo.addRole(userRole);
        } catch (EntityValidationException e) {
            logger.debug("Object failed validation for add(province = {}))", userRole);
            throw new ServiceException("Invalid passed argument. Unable to add new UserRole " + userRole, e);
        } catch (RepoException e) {
            logger.error("Unable to find add( userRole = {}), as it caused: {}", userRole, e.toString());
            throw new ServiceException("Unable to add new role, validation failed", e);
        }
    }

    @Override
    public List<UserRole> getAllRoles() {
        return userRoleRepo.getAllRoles();
    }

    @Override
    public UserRole getByRoleName(UserRole userRole) throws ServiceException {
        logger.info("Queering by using  getByRoleName( userRole = {})", userRole);
        try {
            validateObjectsForNull(userRole);
            return userRoleRepo.getByRoleName(userRole.getRoleName());
        } catch (EntityValidationException e) {
            logger.error("Passed argument failed validation getByRoleName( userRole = {}), cause: {}", userRole, e);
            throw new RepoException("Failed validation", e);
        }
    }

    @Override
    public List<UserRole> getByUserRoleNameLike(UserRole userRole) throws ServiceException {
        logger.info("Queering by using getByUserRoleNameLike( userRole = {})", userRole);
        try {
            validateObjectsForNull(userRole);
            return userRoleRepo.getByUserRoleNameLike(userRole.getRoleName());
        } catch (EntityValidationException e) {
            logger.error("Passed argument failed validation getByRoleName( roleName = {}), cause: {}", userRole, e);
            throw new RepoException("Failed validation", e);
        } catch (RepoException e) {
            logger.error("Unable to find getByUserRoleNameLike( userRole = {}), as it caused: {}", userRole, e.toString());
            throw new ServiceException("Repo failed to get UserRole by: " + userRole, e);
        }
    }

    @Override
    public boolean updateName(UserRole userRole, UserRole newUserRole) throws ServiceException {
        logger.info("Starting changing records in DB with updateName( userRole = {})", userRole);
        try {
            validateObjectsForNull(userRole, newUserRole);
            validateUserRoleFieldsForNulls(userRole, newUserRole);
            userRoleRepo.updateName(userRole, newUserRole);
        } catch (EntityValidationException e) {
            logger.error("Failed validation while updating UserRoles: old = {}, new = {}, cause: {}", userRole, newUserRole, e.toString());
            throw new RepoException(e);
        } catch (RepoException e) {
            logger.error("Unable to find updateName( userRole = {}), as it caused: {}", userRole, e.toString());
            throw new ServiceException("Repo failed to update UserRole: " + userRole.toString() + ", " + newUserRole.toString(), e);
        }
        return false;
    }

    @Override
    public boolean remove(UserRole userRole) throws ServiceException {
        logger.info("Entering remove( userRole = {})", userRole);
        try {
            validateObjectsForNull(userRole);
            validateUserRoleFieldsForNulls(userRole);
            return userRoleRepo.remove(userRole);
        } catch (EntityValidationException e) {
            logger.error("{} failed validation for nulls", userRole);
            throw new RepoException(e);
        } catch (RepoException e) {
            logger.error("Unable to find remove( userRole = {}), as it caused: {}", userRole, e.toString());
            throw new ServiceException("Repo cannot remove userRole: " + userRole.toString(), e);
        }
    }
}
