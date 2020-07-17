package com.application.service;

import com.application.entity.MenuItem;

public interface IMenuItemService {

    MenuItem getByName(String menuItemName);
}
