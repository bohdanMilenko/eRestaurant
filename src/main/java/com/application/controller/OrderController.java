package com.application.controller;

import com.application.entity.Address;
import com.application.entity.Order;
import com.application.entity.OrderStatus;
import com.application.entity.User;
import com.application.entity.dto.OrderDTO;
import com.application.entity.dto.UserDTO;
import com.application.exception.ServiceException;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

//    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
    private IOrderService orderService;

//    @Autowired
    private IUserService userService;
//    @Autowired
    private UserController userController;

    private static ObjectMapper objectMapper = new ObjectMapper();


    public OrderController() {
        modelMapper.addMappings(orderToDTOMapping);
        modelMapper.addMappings(DTOToOrderMapping);
    }

    @Autowired
    public OrderController(ModelMapper modelMapper, IOrderService orderService, IUserService userService, UserController userController) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.userService = userService;
        this.userController = userController;
        modelMapper.addMappings(orderToDTOMapping);
        modelMapper.addMappings(DTOToOrderMapping);
    }

    //    @PostMapping("/date")
//    public void date(@RequestParam("date")
//                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//        // ...
//    }

    @GetMapping
    public ResponseEntity getOrdersForUser(@RequestBody UserDTO userDTO) throws ServiceException, JsonProcessingException {
        List<Order> orderList = orderService.getOrdersByUser(userController.convertToEntity(userDTO));
        orderList.forEach( s -> System.out.println(s.toString()));
        List<OrderDTO> orderDTOList = orderList.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity(objectMapper.writeValueAsString(orderDTOList), HttpStatus.OK);
    }

    PropertyMap<Order, OrderDTO> orderToDTOMapping = new PropertyMap<Order, OrderDTO>() {
        protected void configure() {
            map().setAddressLine(source.getAddress().getAddressLine1());
            map().setOrderedDateTime(source.getOrderedTime());
            map().setOrderStatus(source.getOrderStatus().getOrderStatusName());
            map().setTotalSum(source.getTotalAmount());
        }
    };

    PropertyMap<OrderDTO, Order> DTOToOrderMapping = new PropertyMap<OrderDTO, Order>() {
        protected void configure() {
            map().getAddress().setAddressLine1(source.getAddressLine());
            map().getOrderStatus().setOrderStatusName((source.getOrderStatus()));
            map().setTotalAmount(source.getTotalSum());
            map().setOrderedTime(source.getOrderedDateTime());
        }
    };



//    OrderDTO convertToDto(Order order) {
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class).addMappings(mapper -> {
//            mapper.map( srcOrder -> srcOrder.getAddress().getAddressLine1(), OrderDTO::setAddressLine);
////            mapper.map(srcOrder -> srcOrder.getTotalAmount(), OrderDTO::setTotalSum);
//            mapper.map(srcOrder -> srcOrder.getOrderedTime(), OrderDTO::setOrderedDateTime);
//            mapper.map( srcOrder -> srcOrder.getOrderStatus().getOrderStatusName(), OrderDTO::setOrderStatus);
//
//        });
//        return typeMap.map(order);
////        return modelMapper.map(order, OrderDTO.class);
//    }
//
//    Order convertToEntity(OrderDTO orderDTO) {
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        TypeMap<OrderDTO, Order> typeMap = modelMapper.typeMap( OrderDTO.class, Order.class).addMappings(mapper -> {
//            mapper.map( srcOrderDTO -> new Address(srcOrderDTO.getAddressLine()), Order::setAddress);
//            mapper.map(OrderDTO::getTotalSum, Order::setTotalAmount);
//            mapper.map(OrderDTO::getOrderedDateTime, Order::setOrderedTime);
//            mapper.map( srcOrderDTO -> new OrderStatus(srcOrderDTO.getOrderStatus()), Order::setOrderStatus);
//
//        });
//        return typeMap.map(orderDTO);
////        return modelMapper.map(orderDTO, Order.class);
//    }


}
