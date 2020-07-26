package com.application.service;

import com.application.entity.*;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;


public interface IDishService {

//    void addDish(Dish dish) throws ServiceException;

    void addDishes(Order orderWithDishes) throws ServiceException;

    List<Dish> getAllDishes();

    Optional<Dish> getDishById(int id);

    List<Dish> getDishesByTypeAndStatus(List<String> menuCategories, String dishStatus) throws ServiceException;

    List<Dish> getDishesByOrder(Order order) throws ServiceException;

    List<Dish> getDishesByMenuItem(MenuItem menuItem) throws ServiceException;

    List<Dish> getDishesByOrderAndMenuItem(Order order, MenuItem menuItem) throws ServiceException;

    int getSumByDish(Dish dish) throws ServiceException;

    int getSumByOrder(Order order);

    void pushDishStatusFurther(Dish dish) throws ServiceException;

    void removeDish(Dish dish) throws ServiceException;





}
