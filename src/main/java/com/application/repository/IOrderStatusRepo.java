package com.application.repository;

import com.application.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusRepo extends JpaRepository<OrderStatus, Integer> {

    OrderStatus getOrderStatusByOrderStatusName(String orderStatus);
}
