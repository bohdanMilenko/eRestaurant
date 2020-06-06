package com.application.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu_item_ingredients")
public class MenuItemIngredient {

    @EmbeddedId
    private MenuItemIngredientId menuItemIngredientIdId;

    @ManyToOne
    @MapsId("menuItemId")
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;

    public MenuItemIngredient() {

    }

    public MenuItemIngredient(MenuItemIngredientId menuItemIngredientIdId, MenuItem menuItem, Ingredient ingredient) {
        this.menuItemIngredientIdId = menuItemIngredientIdId;
        this.menuItem = menuItem;
        this.ingredient = ingredient;
    }

    public MenuItemIngredientId getMenuItemIngredientIdId() {
        return menuItemIngredientIdId;
    }

    public void setMenuItemIngredientIdId(MenuItemIngredientId menuItemIngredientIdId) {
        this.menuItemIngredientIdId = menuItemIngredientIdId;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemIngredient that = (MenuItemIngredient) o;
        return  menuItem.equals(that.menuItem) &&
                ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemIngredientIdId, menuItem, ingredient);
    }
}
