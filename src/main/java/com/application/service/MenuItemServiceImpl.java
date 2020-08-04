package com.application.service;

import com.application.entity.MenuCategory;
import com.application.entity.MenuItem;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IMenuItemRepo;
import com.application.util.PassedEntitiesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.*;

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
    public List<MenuCategory> getMenuCategories(@NotNull List<String> menuCategories) throws ServiceException {
        try{
            validateObjectsForNull(menuItemRepo);
            for(String menuC : menuCategories){
                validateObjectsForNull(menuC);
            }
            return menuCategoryService.getMenuCategoriesList(menuCategories);
        }catch (EntityValidationException e){
            log.error("Passed objects to getMenuCategories(List<String> menuCategories) failed validation for nulls: {} ", menuCategories);
            throw new ServiceException("Failed null validation in getMenuCategories(List<String> menuCategories)", e);
        }

    }
}
