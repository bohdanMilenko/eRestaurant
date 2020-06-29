package com.application.entity.dto;

import com.application.entity.Dish;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate orderedDateTime;
    @NotNull
    private String orderStatus;
    @NotNull
    private int totalSum;
//    @NotNull
//    private List<Dish> orderedDishes;
    @NotNull
    private String addressLine;

    public OrderDTO() {
    }

    public OrderDTO(LocalDate orderedDateTime, String orderStatus, int totalSum,
                    List<Dish> orderedDishes, String addressLine) {
        this.orderedDateTime = orderedDateTime;
        this.orderStatus = orderStatus;
        this.totalSum = totalSum;
//        this.orderedDishes = orderedDishes;
        this.addressLine = addressLine;
    }

}
