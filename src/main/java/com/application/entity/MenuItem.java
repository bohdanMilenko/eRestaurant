package com.application.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu_item")
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
    private MenuItemCategory menuItemCategory;

    //Used to be:private List<MenuItemIngredient> ingredientsList = new ArrayList<>();
    @OneToMany(mappedBy = "menuItem")
    private List<MenuItemIngredient> ingredientsList;

    public MenuItem() {
    }

    public MenuItem(String dishName, boolean isKitchenMade, boolean isCurrentlyAvailable,
                    int prepTime, String description, int calories, boolean isBeverage, MenuItemCategory menuItemCategory) {
        this.dishName = dishName;
        this.isKitchenMade = isKitchenMade;
        this.isCurrentlyAvailable = isCurrentlyAvailable;
        this.prepTime = prepTime;
        this.description = description;
        this.calories = calories;
        this.isBeverage = isBeverage;
        this.menuItemCategory = menuItemCategory;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public boolean isKitchenMade() {
        return isKitchenMade;
    }

    public void setKitchenMade(boolean kitchenMade) {
        isKitchenMade = kitchenMade;
    }

    public boolean isCurrentlyAvailable() {
        return isCurrentlyAvailable;
    }

    public void setCurrentlyAvailable(boolean currentlyAvailable) {
        isCurrentlyAvailable = currentlyAvailable;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isBeverage() {
        return isBeverage;
    }

    public void setBeverage(boolean beverage) {
        isBeverage = beverage;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public MenuItemCategory getMenuItemCategory() {
        return menuItemCategory;
    }

    public void setMenuItemCategory(MenuItemCategory menuItemCategory) {
        this.menuItemCategory = menuItemCategory;
    }

    @Override
    public String toString() {
        return "MenuRestaurantItem{" +
                "menuItemId=" + menuItemId +
                ", dishName='" + dishName + '\'' +
                ", isKitchenMade=" + isKitchenMade +
                ", isCurrentlyAvailable=" + isCurrentlyAvailable +
                ", prepTime=" + prepTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", isBeverage=" + isBeverage +
                ", imageLink='" + imageLink + '\'' +
                ", menuItemCategory=" + menuItemCategory +
                '}';
    }
}
