package com.application.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MenuItemIngredientId implements Serializable {

    //TODO - IDEA Highlights these columns for no reason
    @Column(name = "menu_item_id")
    private int menuItemId;
    @Column(name = "ingredient_id")
    private int ingredientId;

    public MenuItemIngredientId() {
    }

    public MenuItemIngredientId(int menuItemId, int ingredientId) {
        this.menuItemId = menuItemId;
        this.ingredientId = ingredientId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemIngredientId that = (MenuItemIngredientId) o;
        return menuItemId == that.menuItemId &&
                ingredientId == that.ingredientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemId, ingredientId);
    }
}
