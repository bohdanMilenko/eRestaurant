package com.application.service;

import com.application.entity.Order;
import com.application.entity.User;
import com.application.exception.ServiceException;

import java.util.List;

public interface IOrderService {

    void addOrder(Order order) throws ServiceException;

    int getOrderById(int id);

    List<Order> getOrdersByUser(User user);

    void updateOrderStatus(Order order);

    void updateDishStatus(Order order, int dishId);

    void remove(Order order);


}
