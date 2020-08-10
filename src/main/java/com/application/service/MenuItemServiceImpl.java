package com.application.service;

import com.application.entity.MenuCategory;
import com.application.entity.MenuItem;
import com.application.entity.dto.ReportingMenuItemDTO;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IMenuItemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;

@Service
@Slf4j
public class MenuItemServiceImpl implements IMenuItemService {

    private final IMenuItemRepo menuItemRepo;
    private final IMenuCategoryService menuCategoryService;

    @Autowired
    public MenuItemServiceImpl(IMenuItemRepo menuItemRepo, IMenuCategoryService menuCategoryService) {
        this.menuItemRepo = menuItemRepo;
        this.menuCategoryService = menuCategoryService;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) throws ServiceException {
        try {
            validateObjectsForNull(menuItem);
            validateObjectsForNull(menuItem.getMenuCategory());
            menuItemRepo.save(menuItem);
        } catch (EntityValidationException e) {
            log.error("Passed objects to addMenuItem(menuItem) failed validation for nulls: {} ", menuItem);
            throw new ServiceException("Failed null validation in addMenuItem(menuItem)", e);
        }

    }

    @Override
    public MenuItem getByName(String menuItemName) {
        return menuItemRepo.getMenuItemByMenuItemName(menuItemName);
    }

    @Override
    public Optional<MenuItem> getMenuItemById(int menuItemId) {
        return menuItemRepo.findById(menuItemId);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepo.findAll();
    }

    @Override
    public List<MenuItem> getMenuItemByCategory(int menuItemCategoryId) {
        return menuItemRepo.getMenuItemByMenuCategory_MenuItemCategoryId(menuItemCategoryId);
    }

    @Override
    public List<MenuCategory> getMenuCategories(List<String> menuCategories) throws ServiceException {
        try {
            validateObjectsForNull(menuItemRepo);
            for (String menuC : menuCategories) {
                validateObjectsForNull(menuC);
            }
            return menuCategoryService.getMenuCategoriesList(menuCategories);
        } catch (EntityValidationException e) {
            log.error("Passed objects to getMenuCategories(List<String> menuCategories) failed validation for nulls: {} ", menuCategories);
            throw new ServiceException("Failed null validation in getMenuCategories(List<String> menuCategories)", e);
        }

    }

    @Override
    public List<ReportingMenuItemDTO> getMenuItemReport(LocalDate startDate, LocalDate endDate, boolean sortAsc) throws ServiceException {
        try {
            validateObjectsForNull(startDate);
            validateObjectsForNull(endDate);
            return menuItemRepo.getSalesByMenuItem(Timestamp.valueOf(startDate.atStartOfDay()), Timestamp.valueOf(endDate.plusDays(1).atStartOfDay()));
        } catch (EntityValidationException e) {
            log.error("Passed objects to getMenuItemReport(startDate = {}, endDate = {}) failed validation for nulls: {} ", startDate, endDate, e.toString());
            throw new ServiceException("Failed null validation in getMenuCategories(List<String> menuCategories)", e);
        }
    }


}
