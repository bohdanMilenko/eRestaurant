package com.application.service;

import com.application.entity.MenuItem;

import java.util.Optional;

public interface IMenuItemService {

    MenuItem getByName(String menuItemName);

    Optional<MenuItem> getMenuItemById(int menuItemId);
}
