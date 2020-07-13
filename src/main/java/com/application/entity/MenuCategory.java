package com.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menu_item_category")
@Data
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_category_id")
    private int menuItemCategoryId;
    @Column(name = "menu_item_category_name")
    private String categoryName;

    public MenuCategory() {
    }

    public MenuCategory( String categoryName) {
        this.categoryName = categoryName;
    }

}
