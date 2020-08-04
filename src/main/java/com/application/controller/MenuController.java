package com.application.controller;

import com.application.entity.MenuCategory;
import com.application.entity.MenuItem;
import com.application.service.IMenuCategoryService;
import com.application.service.IMenuItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("menu")
public class MenuController {

    private IMenuItemService menuItemService;
    private IMenuCategoryService menuCategoryService;

    @GetMapping
    public ResponseEntity<List<MenuCategory>> getMenuCategories(){
        return new ResponseEntity<>(menuCategoryService.getAllMenuItemCategories(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getMenuItems(@RequestParam String menuItemCategoryId){
        if(Integer.parseInt(menuItemCategoryId) != 0){
            return new ResponseEntity<>(menuItemService.getMenuItemByCategory(Integer.parseInt(menuItemCategoryId)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(menuItemService.getAllMenuItems(), HttpStatus.OK);
        }
    }


}
