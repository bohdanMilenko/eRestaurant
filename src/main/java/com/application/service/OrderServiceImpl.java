package com.application.service;

import com.application.entity.Order;
import com.application.entity.OrderStatus;
import com.application.entity.User;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IOrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.PassedEntitiesValidator.validateOrderFieldsForNulls;

@Service
public class OrderServiceImpl implements IOrderService {

    private IOrderRepo orderRepo;
    private IDishService dishService;
    private IDishStatusService dishStatusService;
    private IOrderStatusService orderStatusService;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl() {
    }

    @Autowired
    public OrderServiceImpl(IOrderRepo orderRepo, IDishService dishService, IDishStatusService dishStatusService, IOrderStatusService orderStatusService) {
        this.orderRepo = orderRepo;
        this.dishService = dishService;
        this.dishStatusService = dishStatusService;
        this.orderStatusService = orderStatusService;
    }


    @Override
    @Transactional
    public void addOrder(Order order) throws ServiceException {
        try {
            validateObjectsForNull(order);
            validateOrderFieldsForNulls(order);
            order.setOrderedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            OrderStatus orderStatus = new OrderStatus("Waiting");
            orderStatus = orderStatusService.getByOrderStatusName(orderStatus);
            order.setOrderStatus(orderStatus);
            Order savedOrder = orderRepo.save(order);
            dishService.addDishes(order);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for addOrder(orderRepo = {}))", orderRepo);
            throw new ServiceException("Validation for (nulls) in orderRepo failed: " + orderRepo, e);
        }
    }

    @Override
    public int getOrderById(int id) {
        return 0;
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return null;
    }

    @Override
    public void updateOrderStatus(Order order) {

    }

    @Override
    public void updateDishStatus(Order order, int dishId) {

    }

    @Override
    public void remove(Order order) {

    }
}
