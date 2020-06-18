package com.application.service;

import com.application.entity.Dish;
import com.application.entity.MenuItem;
import com.application.entity.Order;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IDishRepo;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.application.util.StatusUpdate.updateDishStatus;
import static com.application.util.PassedEntitiesValidator.*;

@Service
public class DishServiceImpl implements IDishService {

    private IDishRepo dishRepo;
    private IDishStatusService dishStatusService;

    private static final Logger logger = LoggerFactory.getLogger(DishServiceImpl.class);

    public DishServiceImpl() {
    }

    @Autowired
    public DishServiceImpl(IDishRepo dishRepo, IDishStatusService dishStatusService) {
        this.dishRepo = dishRepo;
        this.dishStatusService = dishStatusService;
    }

    //TODO - I probably don't need to add single dish, as it is easier to perform operations in bulk
//    @Override
//    public void addDish(Dish dish) throws ServiceException {
//        logger.info("Starting writing to DB by using addDish(dish = {})", dish);
//        try {
//            validateObjectsForNull(dish);
//            validateDishForNulls(dish);
//            if (getDishesByOrderAndMenuItem(dish.getOrder(), dish.getMenuItem()) == null) {
//                dishRepo.save(dish);
//            }else {
//                logger.error("Unable to execute addDish (dish = {}), duplicated dish for the same order", dish);
//                throw new ServiceException("Attempt to add duplicated dish for the same order, quantity should be increased instead, " +
//                        "orderId: " + dish.getOrder().getOrderId() + ", menuItemId" + dish.getMenuItem().getMenuItemId());
//            }
//        } catch (EntityValidationException e) {
//            logger.error("Object failed validation for addDish(dish = {}))", dish);
//            throw new ServiceException("Passed entity failed validation: " + dish, e);
//        }
//    }

    //TODO - ADD e.toString() when EntityFailedValidation!!!
    @Override
    public void addDishes(List<Dish> dishes) throws ServiceException {
        try {
            validateObjectsForNull(dishes);
            logger.info("Starting writing to DB by using addDishes(dishes), list size is: {}", dishes.size());
            for (Dish dish : dishes) {
                validateDishForNulls(dish);
            }
            dishRepo.saveAll(dishes);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for addDishes(dishes) list size is: {}", dishes.size());
            throw new ServiceException("Passed entity failed validation: " + dishes, e);
        }
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepo.findAll();
    }

    @Override
    public Optional<Dish> getDishById(int id) {
        return dishRepo.findById(id);
    }

    @Override
    public List<Dish> getDishesByOrder(@Nullable Order order) throws ServiceException {
        try {
            validateObjectsForNull(order);
            validateObjectsForNull(order.getOrderId());
            return dishRepo.getDishesByOrder(order);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getDishesByOrder(order = {}))", order);
            throw new ServiceException("Validation for (nulls) in getDishesByOrder failed: " + order, e);
        }
    }

    @Override
    public List<Dish> getDishesByMenuItem(MenuItem menuItem) throws ServiceException {
        try {
            validateObjectsForNull(menuItem);
            validateObjectsForNull(menuItem.getMenuItemId());
            return dishRepo.getDishesByMenuItem(menuItem);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getDishesByMenuItem(menuItem = {}))", menuItem);
            throw new ServiceException("Validation for (nulls) in getDishesByMenuItem failed: " + menuItem, e);
        }
    }

    @Override
    public List<Dish> getDishesByOrderAndMenuItem(Order order, MenuItem menuItem) throws ServiceException {
        try {
            validateObjectsForNull(order);
            validateObjectsForNull(order.getOrderId());
            validateObjectsForNull(menuItem);
            validateObjectsForNull(menuItem.getMenuItemId());
            return dishRepo.getDishesByOrderAndMenuItem(order, menuItem);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getDishesByOrderAndMenuItem(order = {}, menuItem = {})) and caused: {} ",
                    order, menuItem, e.toString());
            throw new ServiceException("Validation for (nulls) in getDishesByOrderAndMenuItem failed: " + menuItem, e);
        }
    }

    @Override
    public int getSumByDish(Dish dish) throws ServiceException {
        try {
            validateObjectsForNull(dish);
            validateDishForNulls(dish);
            return 0;
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getSumByDish(dish = {}) and caused: {}",
                    dish, e.toString());
            throw new ServiceException("Validation for (nulls) in getDishesByOrderAndMenuItem failed: " + dish, e);
        }
    }

    @Override
    public int getSumByOrder(Order order) {
        return 0;
    }

    @Override
    public void moveDishOneStatusFurther(Dish dish) throws ServiceException {
        try {
            validateObjectsForNull(dish);
            validateDishForNulls(dish);
            Dish dishFromDb = getDishById(dish.getDish_id()).orElseThrow(ServiceException::new);
            updateDishStatus(dishFromDb.getDishStatus());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for moveDishOneStatusFurther(dish = {}) and caused: {}",
                    dish, e.toString());
            throw new ServiceException("Validation for (nulls) in moveDishOneStatusFurther failed: " + dish, e);
        }

    }


    @Override
    public void removeDish(Dish dish) throws ServiceException {
        logger.info("Entering remove( dish = {})", dish);
        try {
            validateObjectsForNull(dish);
            validateDishForNulls(dish);
            if (getDishById(dish.getDish_id()).isPresent()) {
                dishRepo.delete(dish);
            } else {
                logger.error("Unable to remove(dish = {}) - dish does not exist", dish);
                throw new ServiceException("Dish cannot be removed, as it is not present in DB: " + dish.getDish_id());
            }
        } catch (EntityValidationException e) {
            logger.error("{} failed validation for nulls and caused: {}", dish, e.toString());
            throw new ServiceException(e);
        }
    }
}

