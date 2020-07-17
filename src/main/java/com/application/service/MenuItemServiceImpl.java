package com.application.service;

import com.application.entity.MenuItem;
import com.application.repository.IMenuItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemServiceImpl implements IMenuItemService {

    private IMenuItemRepo menuItemRepo;

    public MenuItemServiceImpl() {
    }

    @Autowired
    public MenuItemServiceImpl(IMenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }



    @Override
    public MenuItem getByName(String menuItemName) {
        return menuItemRepo.getMenuItemByDishName(menuItemName);
    }
}
