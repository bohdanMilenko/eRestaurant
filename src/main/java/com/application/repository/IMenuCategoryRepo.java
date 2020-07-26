package com.application.repository;

import com.application.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMenuCategoryRepo extends JpaRepository<MenuCategory,Integer> {

    List<MenuCategory> getMenuItemCategoriesByCategoryNameContains(String menuCategoryName);

    Optional<MenuCategory> getMenuItemCategoriesByCategoryName(String menuCategoryName);

    List<MenuCategory> getMenuCategoriesByCategoryNameIn(List<String> menuCategories);


}
