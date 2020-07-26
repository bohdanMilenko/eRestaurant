package com.application.controller;

import com.application.entity.dto.OrderDTO;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.application.util.dtoEntityConverter.OrderConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping(value = {"/{orderStatus}"})
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable("orderStatus") String orderStatus) {

        return new ResponseEntity<List<OrderDTO>>(OrderConverter.convertOrderListToDTO(orderService.getOrdersByStatus(orderStatus)), HttpStatus.OK);
    }


    //    @PostMapping("/date")
//    public void date(@RequestParam("date")
//                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//        // ...
//    }


}
