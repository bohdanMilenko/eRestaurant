package com.application.controller;

import com.application.entity.Order;
import com.application.entity.User;
import com.application.entity.dto.OrderDTO;
import com.application.entity.dto.UserDTO;
import com.application.exception.ServiceException;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.application.util.dtoEntityConverter.OrderConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;

    public UserController() {
    }

    public UserController(IUserService userService, IOrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping(value ="/createOrder" ,consumes = "application/json")
    public ResponseEntity<HttpStatus> createNewOrder(@RequestBody OrderDTO orderDTO) throws ServiceException {
        try {
            orderService.addOrder(OrderConverter.convertDTOToOrder(orderDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
