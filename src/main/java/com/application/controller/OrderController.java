package com.application.controller;

import com.application.entity.Order;
import com.application.entity.User;
import com.application.entity.dto.OrderDTO;
import com.application.entity.dto.UserDTO;
import com.application.exception.ServiceException;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;
    @Autowired
    private UserController userController;

    private static ObjectMapper objectMapper = new ObjectMapper();

//    @PostMapping("/date")
//    public void date(@RequestParam("date")
//                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//        // ...
//    }

    @GetMapping
    public ResponseEntity getOrdersForUser(@RequestBody UserDTO userDTO) throws ServiceException, JsonProcessingException {
        List<Order> orderList = orderService.getOrdersByUser(userController.convertToEntity(userDTO));
        List<OrderDTO> orderDTOList = orderList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity(objectMapper.writeValueAsString(orderDTOList), HttpStatus.OK);
    }


    OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    Order convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }


}
