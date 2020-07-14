package com.application.controller;

import com.application.entity.Order;
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







}
