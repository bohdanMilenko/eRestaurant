package com.application.controller;

import com.application.entity.MenuCategory;
import com.application.entity.MenuItem;
import com.application.exception.ServiceException;
import com.application.service.IMenuCategoryService;
import com.application.service.IMenuItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("menu")
@AllArgsConstructor
public class MenuController {

    private IMenuItemService menuItemService;
    private IMenuCategoryService menuCategoryService;

    @PostMapping(value = "/creation")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem){
        try{
            menuItemService.addMenuItem(menuItem);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (ServiceException e){
            log.error("MenuController is not able to addMenuItem = {}", menuItem);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<MenuCategory>> getMenuCategories(){
        return new ResponseEntity<>(menuCategoryService.getAllMenuItemCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<List<MenuItem>> getMenuItems(@PathVariable("categoryId") String menuItemCategoryId){
        if(Integer.parseInt(menuItemCategoryId) != 0){
            return new ResponseEntity<>(menuItemService.getMenuItemByCategory(Integer.parseInt(menuItemCategoryId)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(menuItemService.getAllMenuItems(), HttpStatus.OK);
        }
    }


}
