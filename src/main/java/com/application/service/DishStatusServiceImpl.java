package com.application.service;

import com.application.entity.DishStatus;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IDishStatusRepo;
import org.hibernate.action.internal.EntityActionVetoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.validateDishStatusFieldsForNulls;
import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;

@Service
public class DishStatusServiceImpl implements IDishStatusService {

    private IDishStatusRepo dishStatusRepo;
    private static final Logger logger = LoggerFactory.getLogger(DishStatusServiceImpl.class);

    public DishStatusServiceImpl() {
    }

    @Autowired
    public DishStatusServiceImpl(IDishStatusRepo dishStatusRepo) {
        this.dishStatusRepo = dishStatusRepo;
    }

    @Override
    public void addDishStatus(DishStatus dishStatus) throws ServiceException {
        try {
            validateObjectsForNull(dishStatus);
            validateDishStatusFieldsForNulls(dishStatus);
            if (getByName(dishStatus) == null) {
                dishStatusRepo.save(dishStatus);
            } else {
                logger.error("Unable to execute addDishStatus (dishStatus = {}), duplicated cardNetworkType", dishStatus);
                throw new ServiceException("Attempt to add duplicated dishStatus: " + dishStatus.getDishStatusName());
            }
        } catch (EntityActionVetoException e) {
            logger.error("Object failed validation for addDishStatus(dishStatus = {}))", dishStatus);
            throw new ServiceException("Validation for (nulls) in dishStatus failed: " + dishStatus.toString(), e);
        }

    }

    @Override
    public Optional<DishStatus> getById(int id) throws ServiceException {
        try {
            validateObjectsForNull(id);
            return dishStatusRepo.findById(id);
        } catch (EntityActionVetoException e) {
            logger.error("Object failed validation for getById(id = {}))", id);
            throw new ServiceException("Validation for (nulls) in getById failed: " + id, e);
        }
    }

    @Override
    public DishStatus getByName(DishStatus dishStatus) throws ServiceException {
        logger.info("Entering getByName(dishStatus = {})", dishStatus);
        try {
            validateObjectsForNull(dishStatus);
            validateDishStatusFieldsForNulls(dishStatus);
            return dishStatusRepo.getDishStatusByDishStatusName(dishStatus);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getByName(dishStatus = {}))", dishStatus);
            throw new ServiceException("Validation for (nulls) in dishStatus failed: " + dishStatus.toString(), e);
        }
    }

    @Override
    public List<DishStatus> getByNameContains(String dishStatusLike) throws ServiceException {
        logger.info("Entering getByNameContains(dishStatusLike = {})", dishStatusLike);
        try {
            validateObjectsForNull(dishStatusLike);
            return dishStatusRepo.getDishStatusesByDishStatusNameContains(dishStatusLike);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getByNameContains(dishStatusLike = {}))", dishStatusLike);
            throw new ServiceException("Validation for (nulls) in dishStatus failed: " + dishStatusLike, e);
        }
    }

    @Override
    public List<DishStatus> getAllDishStatuses() {
        return dishStatusRepo.findAll();
    }

    @Override
    public void remove(DishStatus dishStatus) throws ServiceException {
        logger.info("Entering remove( userRole = {})", dishStatus);
        try {
            validateObjectsForNull(dishStatus);
            validateDishStatusFieldsForNulls(dishStatus);
            if (getByName(dishStatus) != null) {
                dishStatusRepo.delete(dishStatus);
            } else {
                logger.error("Unable to remove(dishStatus = {}) - dishStatus does not exist", dishStatus);
                throw new ServiceException("DishStatus cannot be removes, as it is not present in DB: " + dishStatus.getDishStatusName());
            }
        } catch (EntityValidationException e) {
            logger.error("{} failed validation for nulls", dishStatus);
            throw new ServiceException(e);
        }
    }
}
