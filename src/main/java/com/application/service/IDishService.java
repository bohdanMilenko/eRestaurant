package com.application.service;

import com.application.entity.Dish;
import com.application.entity.MenuItem;
import com.application.entity.Order;
import com.application.entity.dto.PopularDishReport;
import com.application.exception.ServiceException;

import java.util.List;


public interface IDishService {

//    void addDish(Dish dish) throws ServiceException;

    void addDishes(List<Dish> dishes) throws ServiceException;

    List<Dish> getAllDishes();

    List<Dish> getDishesByOrder(Order order) throws ServiceException;

    List<Dish> getDishesByMenuItem(MenuItem menuItem) throws ServiceException;

    List<Dish> getDishesByOrderAndMenuItem(Order order, MenuItem menuItem) throws ServiceException;

    int getSumByDish(Dish dish) throws ServiceException;

    int getSumByOrder(Order order);

    void moveDishOneStatusFurther(Dish dish);

    boolean updateQuantity(Dish dish);

    List<PopularDishReport> generatePopularDishReport();

    void removeDish(Dish dish);





}
