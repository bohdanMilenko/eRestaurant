package com.application.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DishDTO {

    @NotNull
    private String dishName;
    @NotNull
    private int orderedQuantity;
    private String dishStatus;
    private int price;
    private String imageLink;

    public DishDTO() {
    }

    public DishDTO(String dishName, int orderedQuantity, String dishStatus, int price, String imageLink) {
        this.dishName = dishName;
        this.orderedQuantity = orderedQuantity;
        this.dishStatus = dishStatus;
        this.price = price;
        this.imageLink = imageLink;
    }
}
