package com.application.service;

import com.application.entity.MenuCategory;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IMenuCategoryService {
    
    void addMenuCategory(MenuCategory menuCategory) throws ServiceException;
    
    List<MenuCategory> getMenuCategoryNameLike(MenuCategory menuCategory) throws ServiceException;
    
    Optional<MenuCategory> getMenuCategoryName(MenuCategory menuCategory) throws ServiceException;
    
    List<MenuCategory> getAllMenuItemCategories();
    
    void remove(MenuCategory menuCategory) throws ServiceException;
}
