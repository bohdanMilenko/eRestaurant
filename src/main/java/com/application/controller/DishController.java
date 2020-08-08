package com.application.controller;

import com.application.entity.Dish;
import com.application.exception.ServiceException;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dishes")
@Slf4j
@AllArgsConstructor
public class DishController {

    private final IOrderService orderService;
    private final IDishService dishService;

    private static final List<String> barCategories = new ArrayList<>();
    private static final List<String> sauteCategories = new ArrayList<>();
    private static final List<String> pastrySaladCategories = new ArrayList<>();

    static {
        barCategories.add("Red Wine");
        barCategories.add("White Wine");
        barCategories.add("Non-alcoholic Beverages");
        pastrySaladCategories.add("Appetizers");
        pastrySaladCategories.add("Salads");
        pastrySaladCategories.add("Soups");
        pastrySaladCategories.add("Desserts");
        sauteCategories.add("Mains");
        sauteCategories.add("Sandwiches");
    }


    @GetMapping()
    public ResponseEntity<List<Dish>> getDishes(@RequestParam(value = "stationType", defaultValue = "") String stationType,
                                                @RequestParam(value = "status", defaultValue = "") String status,
                                                @RequestParam(value = "orderId", defaultValue = "") String orderId,
                                                @RequestParam(value = "userId", defaultValue = "") String userId) {
        log.info("Getting dishes in Dish Controller with getDishes(stationType = {}, dishStatus = {})", stationType, status);
        try {
            if(!stationType.equals("") && !status.equals("")) {
                return switch (stationType) {
                    case "bar" -> new ResponseEntity<>(dishService.getDishesByTypeAndStatus(barCategories, status), HttpStatus.OK);
                    case "saute" -> new ResponseEntity<>(dishService.getDishesByTypeAndStatus(sauteCategories, status), HttpStatus.OK);
                    case "pastryAndSalad" -> new ResponseEntity<>(dishService.getDishesByTypeAndStatus(pastrySaladCategories, status), HttpStatus.OK);
                    default -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                };
            }else if( Integer.parseInt(orderId) != 0 && Integer.parseInt(userId) != 0){
                return new ResponseEntity<>(orderService.getOrderByUserAndOrderId(Integer.parseInt(userId),Integer.parseInt(orderId) ).getOrderedDishes(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(dishService.getAllDishes(), HttpStatus.OK);
            }
        } catch (ServiceException e) {
            log.error("DishController failed too getDishesForBar()");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/status"})
    public ResponseEntity<HttpStatus> pushDishStatus(@RequestBody Dish dish) {
        log.info("Updating dish status in Dish Controller with pushDishStatus(dish = {})", dish);
        try {
            dishService.pushDishStatusFurther(dish);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("DishController failed too getDishesForBar()");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}



