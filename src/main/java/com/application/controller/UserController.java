package com.application.controller;

import com.application.entity.Dish;
import com.application.entity.Order;
import com.application.entity.User;
import com.application.entity.dto.DishDTO;
import com.application.entity.dto.OrderDTO;
import com.application.entity.dto.UserDTO;
import com.application.exception.ServiceException;
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

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private IUserService userService;
    private IOrderService orderService;

    public UserController() {
    }

    @Autowired
    public UserController(IUserService userService, IOrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }


    @GetMapping(value = {"/{email}"})
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable String email) {
        try {
            log.info("Controller tries to getOrdersByUser(String email) where email = {}", email);
            return new ResponseEntity<>(OrderConverter.convertOrderListToDTO(orderService.getOrdersByUserEmail(email)), HttpStatus.OK);
        } catch (ServiceException e) {
            log.info("User Controller is not able to process getOrdersByUser(String email) where email = {} and throws: {}", email, e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{email}/{address}/newOrder", consumes = "application/json")
    public ResponseEntity<HttpStatus> createNewOrder(@RequestBody List<DishDTO> dishDTOs, @PathVariable("email") String email, @PathVariable("address") String address) throws ServiceException {
        try {
            List<Dish> dishes = DishConverter.convertToEntity(dishDTOs);
            Order order = new Order();
            order.setUser(new User(email));
            orderService.addOrder(order);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
