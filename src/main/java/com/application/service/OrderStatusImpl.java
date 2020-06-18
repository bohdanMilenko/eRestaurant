package com.application.service;

import com.application.entity.OrderStatus;
import com.application.repository.IOrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusImpl implements IOrderStatus {

    private IOrderStatusRepo orderStatusRepo;

    public OrderStatusImpl() {
    }

    @Autowired
    public OrderStatusImpl(IOrderStatusRepo orderStatusRepo) {
        this.orderStatusRepo = orderStatusRepo;
    }

    @Override
    public void addOrderStatus(OrderStatus orderStatus) {
        orderStatusRepo.save(orderStatus);
    }

    @Override
    public List<OrderStatus> getAll() {
        return orderStatusRepo.findAll();
    }

    @Override
    public Optional<OrderStatus> getById(int id) {

        return orderStatusRepo.findById(id);
    }

    @Override
    public OrderStatus getByOrderStatusName(OrderStatus orderStatus) {

        return orderStatusRepo.getOrderStatusByOrderStatusName(orderStatus.getOrderStatusName());
    }
}
