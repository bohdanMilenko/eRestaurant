package com.application.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class DishDTO {

    private int dishId;
    private int menuItemId;
    @NotNull
    private String menuItemName;
    @NotNull
    private int orderedQuantity;
    private int dishStatusId;
    private String dishStatus;
    private int price;
    private String imageLink;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderedDateTime;

    public DishDTO() {
    }

    public DishDTO(int dishId, int menuItemId, @NotNull String menuItemName, @NotNull int orderedQuantity, int dishStatusId, String dishStatus, int price, String imageLink, LocalDateTime orderedDateTime) {
        this.dishId = dishId;
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.orderedQuantity = orderedQuantity;
        this.dishStatusId = dishStatusId;
        this.dishStatus = dishStatus;
        this.price = price;
        this.imageLink = imageLink;
        this.orderedDateTime = orderedDateTime;
    }
}
