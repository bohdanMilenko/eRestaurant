package com.application.entity.dto;

import com.application.entity.Dish;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    @NotNull
    private LocalDate orderedDateTime;
    @NotNull
    private String orderStatus;
    @NotNull
    private int totalSum;
//    @NotNull
//    private List<Dish> orderedDishes;
    @NotNull
    private String deliveryAddress;

    public OrderDTO() {
    }

    public OrderDTO(LocalDate orderedDateTime, String orderStatus, int totalSum,
                    List<Dish> orderedDishes, String deliveryAddress) {
        this.orderedDateTime = orderedDateTime;
        this.orderStatus = orderStatus;
        this.totalSum = totalSum;
//        this.orderedDishes = orderedDishes;
        this.deliveryAddress = deliveryAddress;
    }

}
