package com.application.service;

import com.application.entity.MenuCategory;
import com.application.entity.MenuItem;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IMenuItemService {

    MenuItem getByName(String menuItemName);

    Optional<MenuItem> getMenuItemById(int menuItemId);

    List<MenuCategory> getMenuCategories(List<String> menuCategories) throws ServiceException;
}
