package com.application.service;

import com.application.entity.Dish;
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
    @Transactional(rollbackOn = ServiceException.class)
    public void addOrder(Order order) throws ServiceException {
        logger.info("Starting adding an order with addOrder(order = {})", order);
        try {
            validateObjectsForNull(order);
            validateOrderFieldsForNulls(order);
            order.setOrderedTime(LocalDateTime.now());
            OrderStatus orderStatus = new OrderStatus("Waiting");
            orderStatus = orderStatusService.getByOrderStatusName(orderStatus);
            order.setOrderStatus(orderStatus);
            User user = order.getUser();
            Optional<User> optionalUser = null;
            if(user.getEmail() == null && user.getUserId()!=0 ){
                optionalUser = userService.getById(user.getUserId());
            }
            if (optionalUser != null && optionalUser.isPresent()){
                user = optionalUser.get();
            }else {
                logger.error("Unable to create order for user = {}, as it doesn't have id or email", user);
                throw new ServiceException("Issue with adding order for user " + user);
            }
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
    @Transactional(rollbackOn = ServiceException.class)
    public void addOrder(List<Dish> dishList, String userEmail, String address) throws ServiceException {
        Order order = new Order();
        User user = userService.getUserByEmail(userEmail);

        order.setUser(user);
        order.setOrderedDishes(dishList);

        addOrder(order);
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return orderRepo.findById(id);
    }

    @Override
    public Order getOrderByUserAndOrderId(int userId, int orderId) throws ServiceException {
        if(userId != 0 && orderId != 0){
            return orderRepo.getOrderByOrderAndUserId(userId,orderId);
        }else {
            logger.error("Passed arguments failed validation. One or both were 0, method: getOrderByUserAndOrderId(userId = {}, orderId = {})", userId, orderId);
            throw new ServiceException("Passed arguments cannot be 0! Method: getOrderByUserAndOrderId(int userId, int orderId)");
        }

    }

    @Override
    public List<Order> getOrdersByUserEmail(String email) throws ServiceException {
        try {
            validateObjectsForNull(email);
            logger.info("Getting Orders by User Email: {}", email);
            List<Order> orders = orderRepo.getOrdersByUser_UserEmail(email);
            return orders;
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getOrdersByUserEmail(userEmail = {}))", email);
            throw new ServiceException("Validation for (nulls) in user failed: " + email, e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int id) throws ServiceException {
        try {
            validateObjectsForNull(id);
            logger.info("Getting Orders by User id: {}", id);
            return orderRepo.getOrdersByUserId(id);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getOrdersByUserId(userId = {}))", id);
            throw new ServiceException("Validation for (nulls) in getOrdersByUserId failed: " + id, e);
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
