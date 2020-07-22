package com.application.service;

import com.application.entity.*;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IDishRepo;
import com.application.repository.IMenuItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.validateDishForNulls;
import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.StatusUpdate.updateDishStatus;

@Service
public class DishServiceImpl implements IDishService {


    //TODO - UPDATE DISH TABLE TO AUTOINCREMENT!!!!!!!!!!!!!!!!!!!!!!
    private IDishRepo dishRepo;
    private IDishStatusService dishStatusService;
    private IMenuItemService menuItemService;
    private IPriceService priceService;

    private static final Logger logger = LoggerFactory.getLogger(DishServiceImpl.class);

    public DishServiceImpl() {
    }

    public DishServiceImpl(IDishRepo dishRepo, IDishStatusService dishStatusService) {
        this.dishRepo = dishRepo;
        this.dishStatusService = dishStatusService;
    }

    @Autowired
    public DishServiceImpl(IDishRepo dishRepo, IDishStatusService dishStatusService, IMenuItemService menuItemService, IPriceService priceService) {
        this.dishRepo = dishRepo;
        this.dishStatusService = dishStatusService;
        this.menuItemService = menuItemService;
        this.priceService = priceService;
    }

    @Override
    @Transactional
    public void addDishes(Order orderWithDishes) throws ServiceException {
        try {
            validateObjectsForNull(orderWithDishes);
            logger.info("Starting writing to DB by using addDishes(dishes), list size is: {}", orderWithDishes.getOrderedDishes().size());
            DishStatus dishStatus = new DishStatus("Waiting");
            dishStatus = dishStatusService.getByName(dishStatus);

            for (Dish dish : orderWithDishes.getOrderedDishes()) {
                Optional<MenuItem> menuItem = menuItemService.getMenuItemById (dish.getMenuItem().getMenuItemId());
                if(menuItem != null && menuItem.isPresent()) {
                    Price price = priceService.getPriceByMenuItem(menuItem.get());
                    dish.setOrder(orderWithDishes);
                    dish.setDishStatus(dishStatus);
                    dish.setPrice(price);
                    dish.setMenuItem(menuItem.get());
                    validateDishForNulls(dish);
                    System.out.println(orderWithDishes.getOrderedDishes().toString());
                }else {
                    logger.error("Unable to create object MenuItem as it contains nulls, either it is null, or menuItemId = 0, Order = {}", orderWithDishes);
                    throw new ServiceException("Order with dishes is incomplete and lacking data to write to DB");
                }
            }
            dishRepo.saveAll(orderWithDishes.getOrderedDishes());
//            orderWithDishes.getOrderedDishes().forEach( dish -> dishRepo.save(dish));
            logger.info("Successfully finished writing to DB with addDishes(List<Dish> dishes= {})", orderWithDishes.getOrderedDishes());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for addDishes(dishes) list size is: {}, it caused: {}", orderWithDishes.getOrderedDishes().size(), e.toString());
            throw new ServiceException("Passed entity failed validation: " + orderWithDishes, e);
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
    public List<Dish> getDishesByOrder(Order order) throws ServiceException {
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
            Dish dishFromDb = getDishById(dish.getDishId()).orElseThrow(ServiceException::new);
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
            if (getDishById(dish.getDishId()).isPresent()) {
                dishRepo.delete(dish);
            } else {
                logger.error("Unable to remove(dish = {}) - dish does not exist", dish);
                throw new ServiceException("Dish cannot be removed, as it is not present in DB: " + dish.getDishId());
            }
        } catch (EntityValidationException e) {
            logger.error("{} failed validation for nulls and caused: {}", dish, e.toString());
            throw new ServiceException(e);
        }
    }
}

