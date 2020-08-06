package com.application.controller;

import com.application.entity.Dish;
import com.application.entity.Order;
import com.application.exception.ServiceException;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/orders")
@Slf4j
@AllArgsConstructor
public class OrderController {


    private IOrderService orderService;
    private IUserService userService;
    private UserController userController;

    private static ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping()
    public ResponseEntity<List<Order>> getOrders(@RequestParam("orderStatus") String orderStatus,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (!orderStatus.isEmpty()) {
            log.info("Order controller uses getOrders( orderStatus = {}) ", orderStatus);
            return new ResponseEntity<>(orderService.getOrdersByStatus(orderStatus), HttpStatus.OK);
        } else if (startDate != null && endDate != null) {
            try {
                log.info("Order controller uses getOrders( startDate = {}, endDate = {}) ", startDate.toString(), endDate.toString());
                return new ResponseEntity<>(orderService.getOrdersByDate(startDate, endDate), HttpStatus.OK);
            } catch (ServiceException e) {
//                log.error("OrderController is not able to get Orders for user = {}, error message: {}", userId, e.toString());
            }
        } else {

            return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(value = {"/{userId}"})
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable String userId) {
        try {
            log.info("OrderController tries to getOrdersForUser(String userId) where id = {}", userId);
            return new ResponseEntity<>(orderService.getOrdersByUserId(Integer.parseInt(userId)), HttpStatus.OK);
        } catch (ServiceException e) {
            log.info("OrderController is not able to process getOrdersByUser(String id) where id = {} and throws: {}", userId, e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "{userId}/{orderId}", consumes = "application/json")
    public ResponseEntity<List<Dish>> getOrderDetails(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId) {
        try {
            log.info("Starting getOrderDetails( String userId ={}, String orderId={})", userId, orderId);
            return new ResponseEntity<>(orderService.getOrderByUserAndOrderId(Integer.parseInt(userId), Integer.parseInt(orderId)).getOrderedDishes(), HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("OrderService was not able to process getOrderDetails request: {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<HttpStatus> createNewOrder(@RequestBody Order order) throws ServiceException {
        try {
            log.info("Started createNewOrder(order = {})", order);
            if (order != null && order.getUser() != null && order.getUser().getUserId() != 0) {
                orderService.addOrder(order);
            } else {
                orderService.addAnonymousOrder(order);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Endpoint addOrder(Order = {}) is unable to process the request.", order);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
