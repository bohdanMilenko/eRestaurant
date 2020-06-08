package com.application.service;

import com.application.entity.User;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.PassedEntitiesValidator.validateUserFieldsForNulls;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    @Transactional
    public void addUser(User user) throws ServiceException {
        logger.info("Starting writing to DB by using addUser(user = {})", user);
        try {
            validateObjectsForNull(user);
            validateUserFieldsForNulls(user);
            if (getUserByEmail(user.getEmail()) == null) {
                userRepo.save(user);
            } else {
                logger.error("Unable to execute addUser (user = {}), duplicated user", user);
                throw new ServiceException("Attempt to add duplicated user: " + user.getUserId());
            }
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for add(province = {}))", user);
            throw new ServiceException("Validation for (nulls) in User failed: " + user.toString(), e);
        }

    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getById(int id) throws ServiceException {
        logger.info("Queering by using  getById( email = {})", id);
        try {
            validateObjectsForNull(id);
            return userRepo.findById(id);
        } catch (EntityValidationException e) {
            logger.error("Passed argument failed validation getById( email = {}), cause: {}", id, e);
            throw new ServiceException("Failed validation", e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        logger.info("Queering by using  getUserByEmail( email = {})", email);
        try {
            validateObjectsForNull(email);
            return userRepo.getByEmail(email);
        } catch (EntityValidationException e) {
            logger.error("Passed argument failed validation getUserByEmail( email = {}), cause: {}", email, e);
            throw new ServiceException("Failed validation", e);
        }
    }

    @Override
    public List<User> getUserByNameLike(String name) throws ServiceException {
        logger.info("Queering by using  getUserByNameLike( name = {})", name);
        try {
            validateObjectsForNull(name);
            return userRepo.getByNameContains(name);
        } catch (EntityValidationException e) {
            logger.error("Passed argument failed validation getUserByNameLike( name = {}), cause: {}", name, e);
            throw new ServiceException("Failed validation", e);
        }
    }

    @Override
    public List<User> getUserByUserRoleName(String userRoleName) throws ServiceException {
        logger.info("Queering by using  getUserByUserRoleName( userRoleName = {})", userRoleName);
        try {
            validateObjectsForNull(userRoleName);
            return userRepo.getUserByUserRole(userRoleName);
        } catch (EntityValidationException e) {
            logger.error("Passed argument failed validation getUserByUserRoleName( userRoleName = {}), cause: {}", userRoleName, e);
            throw new ServiceException("Failed validation", e);
        }
    }

    @Override
    public User authenticateUser(User user) throws ServiceException {
        logger.info("Queering by using authenticateUser( user = {})", user);
        try {
            validateObjectsForNull(user);
            validateUserFieldsForNulls(user);
            User fetchedUser = getUserByEmail(user.getEmail());
            if (fetchedUser != null) {
                return userRepo.getByUserIdAndPassword(fetchedUser.getUserId(), user.getPassword());
            } else {
                logger.error("Unable to execute authenticateUser (user = {}), user does not exist", user);
                throw new ServiceException("User must register before he can authenticate: " + user.getUserId());
            }
        } catch (EntityValidationException e) {
            logger.error("Passed argument failed validation authenticateUser( user = {}), cause: {}", user, e);
            throw new ServiceException("Failed validation", e);
        }
    }

    @Override
    public void deactivateUser(User user) throws ServiceException {
        logger.info("Entering deactivateUser( user = {})", user);
        try {
            validateObjectsForNull(user);
            validateUserFieldsForNulls(user);
            User foundUser = getUserByEmail(user.getEmail());
            if (foundUser != null) {
                foundUser.setActive(false);
            } else {
                logger.error("Unable to execute deactivateUser (user = {}), user does not exist", user);
                throw new ServiceException("User cannot be deactivated, as it is not present in DB: " + user.getUserId());
            }
        } catch (EntityValidationException e) {
            logger.error("{} failed validation for nulls", user);
            throw new ServiceException(e);
        }
    }


}
