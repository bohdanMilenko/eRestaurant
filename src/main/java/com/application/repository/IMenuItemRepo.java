package com.application.repository;

import com.application.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMenuItemRepo extends JpaRepository<MenuItem,Integer> {

    MenuItem getMenuItemByMenuItemName(String menuItemName);

    List<MenuItem> getMenuItemByMenuCategory_MenuItemCategoryId(int menuCategoryId);

}
