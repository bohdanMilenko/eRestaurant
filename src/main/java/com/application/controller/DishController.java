package com.application.controller;

import com.application.entity.dto.DishDTO;
import com.application.exception.ServiceException;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import com.application.util.dtoEntityConverter.DishConverter;
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
public class DishController {

    private final IOrderService orderService;
    private final IDishService dishService;

    private static List<String> barCategories = new ArrayList<>();
    private static List<String> sauteCategories = new ArrayList<>();
    private static List<String> pastrySaladCategories = new ArrayList<>();

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

    @Autowired
    public DishController(IOrderService orderService, IDishService dishService) {
        this.orderService = orderService;
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<DishDTO>> getTodayDishes() {
        return null;
    }

    //TODO - create flow
    @GetMapping()
    public ResponseEntity<List<DishDTO>> getDishesByStationAndStatus(@RequestParam("stationType") String stationType, @RequestParam("status") String status) {
        log.info("Getting dishes in Dish Controller with getDishesByStationAndStatus(stationType = {}, dishStatus = {})", stationType, status);
        try {
            return switch (stationType) {
                case "bar" -> new ResponseEntity<>(DishConverter.convertToDto(dishService.getDishesByTypeAndStatus(barCategories, status)), HttpStatus.OK);
                case "saute" -> new ResponseEntity<>(DishConverter.convertToDto(dishService.getDishesByTypeAndStatus(sauteCategories, status)), HttpStatus.OK);
                case "pastryAndSalad" -> new ResponseEntity<>(DishConverter.convertToDto(dishService.getDishesByTypeAndStatus(pastrySaladCategories, status)), HttpStatus.OK);
                default -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            };
        } catch (ServiceException e) {
            log.error("DishController failed too getDishesForBar()");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/status"})
    public ResponseEntity<HttpStatus> pushDishStatus(@RequestBody DishDTO dishDTO) {
        log.info("Updating dish status in Dish Controller with pushDishStatus(dishDTO = {})", dishDTO);
        try {
            dishService.pushDishStatusFurther(DishConverter.convertToEntity(dishDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("DishController failed too getDishesForBar()");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
