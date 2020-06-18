package com.application.service;

import com.application.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface IOrderStatus {

    void addOrderStatus(OrderStatus orderStatus);

    List<OrderStatus> getAll();

    Optional<OrderStatus> getById(int id);

    OrderStatus getByOrderStatusName(OrderStatus orderStatus);


}
