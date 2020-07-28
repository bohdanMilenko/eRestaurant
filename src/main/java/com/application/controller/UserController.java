package com.application.controller;

import com.application.entity.*;
import com.application.entity.dto.AddressDTO;
import com.application.entity.dto.DishDTO;
import com.application.entity.dto.OrderDTO;
import com.application.exception.ServiceException;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import com.application.util.dtoEntityConverter.AddressConverter;
import com.application.util.dtoEntityConverter.DishConverter;
import com.application.util.dtoEntityConverter.OrderConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private IUserService userService;
    private IOrderService orderService;
    private IDishService dishService;

    @GetMapping(value = {"/{userId}/orders"})
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable String userId) {
        try {
            log.info("Controller tries to getOrdersByUser(String userId) where id = {}", userId);
            return new ResponseEntity<>(OrderConverter.convertOrderListToDTO(orderService.getOrdersByUserId(Integer.parseInt(userId))), HttpStatus.OK);
        } catch (ServiceException e) {
            log.info("User Controller is not able to process getOrdersByUser(String id) where id = {} and throws: {}", userId, e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "{userId}/orders/{orderId}", consumes = "application/json")
    public ResponseEntity<List<DishDTO>> getOrderDetails(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId) {
        Optional<Order> order = orderService.getOrderById(Integer.parseInt(orderId));
        return order.map(value -> new ResponseEntity<>(DishConverter.convertToDto(value.getOrderedDishes()), HttpStatus.OK)).orElse(null);
    }

    @PostMapping(value = "{userId}/personalInfo", consumes = "application/json")
    public ResponseEntity<HttpStatus> addUserAddress(@PathVariable("userId") String userId, @RequestBody AddressDTO addressDTO) {
        try {
            log.info("Starting addUserAddress( userId = {}, addressDTO = {})", userId, addressDTO);
            Address address = AddressConverter.convertDTOToAddress(addressDTO);
            address.setUser(new User(Integer.parseInt(userId)));
            address.setProvince(new Province(addressDTO.getProvinceId(), addressDTO.getProvince()));
            log.info("Converted object looks like: address = {}", address);
            userService.addAddressForUser(Integer.parseInt(userId), address);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("User controller cannot addUserAddress( userId = {}, addressDTO = {})", userId, addressDTO);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
