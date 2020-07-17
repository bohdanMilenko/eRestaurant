package com.application.service;

import com.application.entity.Dish;
import com.application.entity.Order;
import com.application.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IOrderService {

    void addOrder(Order order) throws ServiceException;

    void addOrder(List<Dish> dishList, String userEmail, String address) throws ServiceException;

    Optional<Order> getOrderById(int id);

    List<Order> getOrdersByUserEmail(String email) throws ServiceException;

    List<Order> getOrdersByDate(LocalDate startDate, LocalDate endDate) throws ServiceException;

    void updateOrderStatus(Order order) throws ServiceException;

    void updateDishStatus(Order order, int dishId);

    void remove(Order order);



}
