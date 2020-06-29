package com.application.service;

import com.application.entity.Order;
import com.application.entity.OrderStatus;
import com.application.entity.User;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IOrderRepo;
import com.application.util.StatusUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.PassedEntitiesValidator.validateOrderFieldsForNulls;

@Service
public class OrderServiceImpl implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private IOrderRepo orderRepo;
    private IDishService dishService;
    private IDishStatusService dishStatusService;
    private IOrderStatusService orderStatusService;
    private IUserService userService;

    public OrderServiceImpl() {
    }

    @Autowired
    public OrderServiceImpl(IOrderRepo orderRepo, IDishService dishService, IDishStatusService dishStatusService,
                            IOrderStatusService orderStatusService, IUserService userService) {
        this.orderRepo = orderRepo;
        this.dishService = dishService;
        this.dishStatusService = dishStatusService;
        this.orderStatusService = orderStatusService;
        this.userService = userService;
    }

    @Override
    @Transactional()
    public void addOrder(Order order) throws ServiceException {
        try {
            //TODO - Check Lombok
            validateObjectsForNull(order);
            validateOrderFieldsForNulls(order);
            //todo - converter LocalDateTime
            order.setOrderedTime(LocalDateTime.now());
            OrderStatus orderStatus = new OrderStatus("Waiting");
            orderStatus = orderStatusService.getByOrderStatusName(orderStatus);
            order.setOrderStatus(orderStatus);
            User user = order.getUser();
            User userFromDB = userService.getUserByEmail(user.getEmail());
            if (userFromDB == null) {
                userService.addUser(order.getUser());
            }
            Order savedOrder = orderRepo.save(order);
            dishService.addDishes(order);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for addOrder(order = {}))", order);
            throw new ServiceException("Validation for (nulls) in order failed: " + order, e);
        }
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return orderRepo.findById(id);
    }

    @Override
    public List<Order> getOrdersByUser(User user) throws ServiceException {
        try {
            validateObjectsForNull(user);
            validateObjectsForNull(user.getUserId());
            return orderRepo.getOrdersByUser_UserId(user.getUserId());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getOrdersByUser(user = {}))", user);
            throw new ServiceException("Validation for (nulls) in user failed: " + user, e);
        }
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate startDate, LocalDate endDate) throws ServiceException {
        try {
            validateObjectsForNull(startDate);
            return orderRepo.getOrdersByOrderedTimeBetween(Timestamp.valueOf(startDate.atStartOfDay()), Timestamp.valueOf(endDate.atStartOfDay()));
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getOrdersByDate(startDate = {}, endDate = {}))", startDate, endDate);
            throw new ServiceException("Validation for (nulls) in getOrdersByDate failed", e);
        }
    }

    @Override
    public void updateOrderStatus(Order order) throws ServiceException {
        try {
            logger.info("Updating order status to {} - id: {}", order.getOrderStatus(), order.getOrderId());
            validateObjectsForNull(order);
            validateObjectsForNull(order.getOrderId());
            Optional<Order> orderFromDB = getOrderById(order.getOrderId());
            if (orderFromDB.isPresent()) {
                System.out.println(orderFromDB.get().getOrderStatus().toString());
                StatusUpdate.updateOrderStatus(orderFromDB.get().getOrderStatus());
                System.out.println(orderFromDB.get().getOrderStatus().toString());
            } else {
                logger.error("Cannot execute updateOrderStatus(order = {}), as this order is not in DB yet)", order);
                throw new ServiceException("Such order does not exist in DB! " + order.toString());
            }
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for updateOrderStatus(order = {}))", order);
            throw new ServiceException("Validation for (nulls) in updateOrderStatus failed", e);
        }

    }

    @Override
    public void updateDishStatus(Order order, int dishId) {

    }

    @Override
    public void remove(Order order) {

    }
}
