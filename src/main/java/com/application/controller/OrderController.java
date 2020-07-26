package com.application.controller;

import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {


    private IOrderService orderService;
    private IUserService userService;
    private UserController userController;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public OrderController(IOrderService orderService, IUserService userService, UserController userController) {
        this.orderService = orderService;
        this.userService = userService;
        this.userController = userController;
    }

    //    @PostMapping("/date")
//    public void date(@RequestParam("date")
//                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//        // ...
//    }


}
