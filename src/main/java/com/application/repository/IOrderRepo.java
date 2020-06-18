package com.application.repository;

import com.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepo extends JpaRepository<Order, Integer> {

    List<Order> getOrdersByUser_UserId(int userId);

    List<Order> getOrdersByOrderStatus_OrderStatusName(String orderStatus);




}
