package com.application.controller;

import com.application.entity.Address;
import com.application.entity.Dish;
import com.application.entity.Order;
import com.application.entity.User;
import com.application.exception.ServiceException;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import com.application.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private IUserService userService;
    private IOrderService orderService;
    private IDishService dishService;


    @PostMapping(value = "/registration")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody User user) {
        try {
            log.info("UserController is adding a new user through addNewUser(user={})", user);
            userService.addUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ServiceException e) {
            log.error("UserController cannot process the request too addNewUser {}", user);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{userId}/profile/address/creation", consumes = "application/json")
    public ResponseEntity<HttpStatus> addUserAddress(@PathVariable("userId") String userId, @RequestBody Address address) {
        try {
            log.info("Starting addUserAddress( userId = {}, addressDTO = {})", userId, address);
            address.setUser(new User(Integer.parseInt(userId)));
            userService.addAddressForUser(address);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("User controller cannot addUserAddress( userId = {}, addressDTO = {})", userId, address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{userId}/profile/address")
    public ResponseEntity<List<Address>> getAddressesForUser(@PathVariable("userId") String userId) {
        try {
            return new ResponseEntity<>(userService.getUserAddresses(Integer.parseInt(userId)), HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("UserController is unable to process getAddressesForUser( userId = {} )", userId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
