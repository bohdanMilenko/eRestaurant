package com.application.controller;

import com.application.entity.*;
import com.application.entity.dto.DishDTO;
import com.application.entity.dto.OrderDTO;
import com.application.entity.dto.UserDTO;
import com.application.exception.ServiceException;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.application.util.dtoEntityConverter.DishConverter;
import com.application.util.dtoEntityConverter.OrderConverter;
import com.application.util.dtoEntityConverter.UserConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private IUserService userService;
    private IOrderService orderService;
    private IDishService dishService;

    public UserController() {
    }

    @Autowired
    public UserController(IUserService userService, IOrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }


    @GetMapping(value = {"/{userId}"})
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable String id) {
        try {
            log.info("Controller tries to getOrdersByUser(String userId) where id = {}", id);
            return new ResponseEntity<>(OrderConverter.convertOrderListToDTO(orderService.getOrdersByUserId(Integer.parseInt(id))), HttpStatus.OK);
        } catch (ServiceException e) {
            log.info("User Controller is not able to process getOrdersByUser(String id) where id = {} and throws: {}", id, e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{userId}/order", consumes = "application/json")
    public ResponseEntity<HttpStatus> createNewOrder(@RequestBody OrderDTO orderDTO, @PathVariable("userId") String id) throws ServiceException {
        try {
            Order order = OrderConverter.convertDTOToOrder(orderDTO);
            order.setOrderedDishes(DishConverter.convertToEntity(orderDTO.getDishList()));
            order.setAddress(new Address(orderDTO.getAddressId(), orderDTO.getAddressLine()));
            order.setUser(new User(Integer.parseInt(id)));
            order.setPaymentMethod(new PaymentMethod(orderDTO.getPaymentMethodId()));
            orderService.addOrder(order);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{userId}/order/{orderId}", consumes = "application/json")
    public ResponseEntity<List<DishDTO>> getOrderDetails(@PathVariable("userId") String userId, @PathVariable("orderId")String orderId){
        Optional<Order> order = orderService.getOrderById(Integer.parseInt(orderId));
        return order.map(value -> new ResponseEntity<>(DishConverter.convertToDto(value.getOrderedDishes()), HttpStatus.OK)).orElse(null);
    }


}
