package com.application.controller;

import com.application.entity.Dish;
import com.application.entity.Order;
import com.application.entity.dto.DishDTO;
import com.application.exception.ServiceException;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dish")
@Slf4j
public class DishController {

    private final IOrderService orderService;
    private final IDishService dishService;

    private static List<String> barCategories = new ArrayList<>();

    static {
        barCategories.add("Red Wine");
        barCategories.add("White Wine");
        barCategories.add("Non-alcoholic Beverages");
    }

    @Autowired
    public DishController(IOrderService orderService, IDishService dishService) {
        this.orderService = orderService;
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<DishDTO>> getTodayDishes(){
        return null;
    }

    @GetMapping(value = {"/bar"})
    public ResponseEntity<List<Dish>> getDishesForBar(){
        try {
            return new ResponseEntity<>(dishService.getDishesByTypeAndStatus(barCategories, "Pending"), HttpStatus.OK);
        }catch (ServiceException e){
            log.error("DishController failed too getDishesForBar()");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
