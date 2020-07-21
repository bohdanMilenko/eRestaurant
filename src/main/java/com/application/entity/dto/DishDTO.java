package com.application.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DishDTO {

    private int menuItemId;
    @NotNull
    private String menuItemName;
    @NotNull
    private int orderedQuantity;
    private String dishStatus;
    private int price;
    private String imageLink;

    public DishDTO() {
    }

    public DishDTO(int menuItemId, @NotNull String menuItemName, @NotNull int orderedQuantity, String dishStatus, int price, String imageLink) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.orderedQuantity = orderedQuantity;
        this.dishStatus = dishStatus;
        this.price = price;
        this.imageLink = imageLink;
    }
}
