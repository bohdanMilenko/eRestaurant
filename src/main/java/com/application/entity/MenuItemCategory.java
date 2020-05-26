package com.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "menu_item_category")
public class MenuItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_category_id")
    private int menuItemCategoryId;
    @Column(name = "menu_item_category_name")
    private String categoryName;

    public MenuItemCategory() {
    }

    public MenuItemCategory(int menuItemCategoryId, String categoryName) {
        this.menuItemCategoryId = menuItemCategoryId;
        this.categoryName = categoryName;
    }

    public int getMenuItemCategoryId() {
        return menuItemCategoryId;
    }

    public void setMenuItemCategoryId(int menuItemCategoryId) {
        this.menuItemCategoryId = menuItemCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "MenuItemCategory{" +
                "menuItemCategoryId=" + menuItemCategoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
