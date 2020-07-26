package com.application.service;

import com.application.entity.MenuCategory;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.IMenuCategoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.validateMenuCategoryForNulls;
import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;

@Service
public class MenuCategoryServiceImpl implements IMenuCategoryService {

    private IMenuCategoryRepo menuCategoryRepo;

    private static final Logger logger = LoggerFactory.getLogger(MenuCategoryServiceImpl.class);

    public MenuCategoryServiceImpl() {
    }

    @Autowired
    public MenuCategoryServiceImpl(IMenuCategoryRepo menuCategoryRepo) {
        this.menuCategoryRepo = menuCategoryRepo;
    }

    @Override
    public void addMenuCategory(MenuCategory menuCategory) throws ServiceException {
        try {
            validateObjectsForNull(menuCategory);
            validateMenuCategoryForNulls(menuCategory);
            if (getMenuCategoryName(menuCategory).isEmpty()) {
                menuCategoryRepo.save(menuCategory);
            } else {
                logger.error("Unable to execute addMenuCategory (menuCategory = {}), duplicated record in DB", menuCategory);
                throw new ServiceException("Attempt to add duplicate: " + menuCategory.getCategoryName());
            }
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for addMenuCategory(menuCategory = {}))", menuCategory);
            throw new ServiceException("Validation for (nulls) in menuCategory failed: " + menuCategory, e);
        }

    }

    @Override
    public Optional<MenuCategory> getMenuCategoryName(MenuCategory menuCategory) throws ServiceException {
        try {
            validateObjectsForNull(menuCategory);
            validateMenuCategoryForNulls(menuCategory);
            return menuCategoryRepo.getMenuItemCategoriesByCategoryName(menuCategory.getCategoryName());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getMenuCategoryName(menuCategory = {}))", menuCategory);
            throw new ServiceException("Validation for (nulls) in menuCategory failed: " + menuCategory, e);
        }
    }

    @Override
    public List<MenuCategory> getMenuCategoryNameLike(MenuCategory menuCategory) throws ServiceException {
        try {
            validateObjectsForNull(menuCategory);
            validateMenuCategoryForNulls(menuCategory);
            return menuCategoryRepo.getMenuItemCategoriesByCategoryNameContains(menuCategory.getCategoryName());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getMenuCategoryNameLike(menuCategory = {}))", menuCategory);
            throw new ServiceException("Validation for (nulls) in menuCategory failed: " + menuCategory, e);
        }
    }

    @Override
    public List<MenuCategory> getMenuCategoriesList(List<String> menuCategories) {

        return menuCategoryRepo.getMenuCategoriesByCategoryNameIn(menuCategories);
    }

    @Override
    public List<MenuCategory> getAllMenuItemCategories() {
        return menuCategoryRepo.findAll();
    }

    @Override
    public void remove(MenuCategory menuCategory) throws ServiceException {
        try {
            validateObjectsForNull(menuCategory);
            validateMenuCategoryForNulls(menuCategory);
            if (getMenuCategoryName(menuCategory).isPresent()) {
                menuCategoryRepo.delete(menuCategory);
            } else {
                logger.error("Unable to remove(menuCategory = {}) user does not exist", menuCategory);
                throw new ServiceException("MenuCategory cannot be removed, as it is not present in DB: " + menuCategory.getCategoryName());
            }
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getMenuCategoryNameLike(menuCategory = {}))", menuCategory);
            throw new ServiceException("Validation for (nulls) in menuCategory failed: " + menuCategory, e);
        }
    }
}
