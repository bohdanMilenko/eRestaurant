package com.application.service;

import com.application.entity.MenuCategory;
import com.application.entity.MenuItem;
import com.application.entity.dto.ReportingMenuItemDTO;
import com.application.entity.dto.ReportingOrdersDTO;
import com.application.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMenuItemService {

    void addMenuItem(MenuItem menuItem) throws ServiceException;

    MenuItem getByName(String menuItemName);

    Optional<MenuItem> getMenuItemById(int menuItemId);

    List<MenuItem> getAllMenuItems();

    List<MenuItem> getMenuItemByCategory(int menuItemCategoryId);

    List<MenuCategory> getMenuCategories(List<String> menuCategories) throws ServiceException;

    List<ReportingMenuItemDTO> getMenuItemReport(LocalDate startDate, LocalDate endDate, boolean sortAsc) throws ServiceException;
}
