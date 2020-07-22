package com.application.repository;

import com.application.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuItemRepo extends JpaRepository<MenuItem,Integer> {

    MenuItem getMenuItemByMenuItemName(String menuItemName);

}
