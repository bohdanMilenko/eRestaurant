package com.application.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu_item")
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private int menuItemId;
    @Column(name = "dish_name")
    private String dishName;
    @Column(name = "is_kitchen_made")
    private boolean isKitchenMade;
    @Column(name = "is_currently_availaible")
    private boolean isCurrentlyAvailable;
    @Column(name = "prep_time")
    private int prepTime;
    @Column(name = "description")
    private String description;
    @Column(name = "calories")
    private int calories;
    @Column(name = "is_beverage")
    private boolean isBeverage;
    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "menu_item_category_id")
    private MenuCategory menuCategory;

    //Used to be:private List<MenuItemIngredient> ingredientsList = new ArrayList<>();
//    @OneToMany(mappedBy = "menuItem")
//    private List<MenuItemIngredient> ingredientsList;

    public MenuItem() {
    }

    public MenuItem(String dishName) {
        this.dishName = dishName;
    }


    public MenuItem(String dishName, boolean isKitchenMade, boolean isCurrentlyAvailable,
                    int prepTime, String description, int calories, boolean isBeverage, MenuCategory menuCategory) {
        this.dishName = dishName;
        this.isKitchenMade = isKitchenMade;
        this.isCurrentlyAvailable = isCurrentlyAvailable;
        this.prepTime = prepTime;
        this.description = description;
        this.calories = calories;
        this.isBeverage = isBeverage;
        this.menuCategory = menuCategory;
    }


}
