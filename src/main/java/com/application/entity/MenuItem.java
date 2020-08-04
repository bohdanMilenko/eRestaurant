package com.application.entity;

import com.application.util.jsonSerializer.MenuItemSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menu_item")
@Data
@JsonSerialize(using = MenuItemSerializer.class)
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    @JsonProperty("menuItemId")
    private int menuItemId;

    @Column(name = "dish_name")
    @JsonProperty("menuItemName")
    private String menuItemName;

    @Column(name = "is_kitchen_made")
    @JsonProperty("isKitchenMade")
    private boolean isKitchenMade;

    @Column(name = "is_currently_availaible")
    @JsonProperty("isCurrentlyAvailable")
    private boolean isCurrentlyAvailable;

    @Column(name = "prep_time")
    @JsonProperty("prepTime")
    private int prepTime;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "calories")
    @JsonProperty("calories")
    private int calories;

    @Column(name = "is_beverage")
    @JsonProperty("isBeverage")
    private boolean isBeverage;

    @Column(name = "image_link")
    @JsonProperty("imageLink")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "menu_item_category_id")
    @JsonProperty("menuCategory")
    private MenuCategory menuCategory;

    //Used to be:private List<MenuItemIngredient> ingredientsList = new ArrayList<>();
//    @OneToMany(mappedBy = "menuItem")
//    private List<MenuItemIngredient> ingredientsList;

    public MenuItem() {
    }

    public MenuItem(int menuItemId, String menuItemName) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
    }

    public MenuItem(String menuItemName) {
        this.menuItemName = menuItemName;
    }


    public MenuItem(String menuItemName, boolean isKitchenMade, boolean isCurrentlyAvailable,
                    int prepTime, String description, int calories, boolean isBeverage, MenuCategory menuCategory) {
        this.menuItemName = menuItemName;
        this.isKitchenMade = isKitchenMade;
        this.isCurrentlyAvailable = isCurrentlyAvailable;
        this.prepTime = prepTime;
        this.description = description;
        this.calories = calories;
        this.isBeverage = isBeverage;
        this.menuCategory = menuCategory;
    }


}
