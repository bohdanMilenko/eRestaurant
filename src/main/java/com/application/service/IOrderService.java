package com.application.service;

import com.application.entity.Dish;
import com.application.entity.Order;
import com.application.entity.dto.ReportingOrdersDTO;
import com.application.exception.ServiceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrderService {

    void addOrder(Order order) throws ServiceException;

    void addAnonymousOrder(Order order) throws ServiceException;

    void addOrder(List<Dish> dishList, String userEmail, String address) throws ServiceException;

    List<Order> getAllOrders();

    Optional<Order> getOrderById(int id);

    List<Order> getOrdersByStatus(String orderStatus);

    Order getOrderByUserAndOrderId(int userId, int orderId) throws ServiceException;

    List<Order> getOrdersByUserEmail(String email) throws ServiceException;

    List<Order> getOrdersByUserId(int id) throws ServiceException;

    List<Order> getOrdersByDate(LocalDate startDate, LocalDate endDate) throws ServiceException;

    void updateOrderStatus(Order order) throws ServiceException;

    void remove(Order order);

    List<ReportingOrdersDTO> getOrderReport(LocalDateTime startDate, LocalDateTime endDate, boolean sortAsc) throws ServiceException;



}
