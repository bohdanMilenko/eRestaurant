package com.application.entity.dto;

import com.application.entity.Dish;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDateTime orderedDateTime;
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

    public OrderDTO(LocalDateTime orderedDateTime, String orderStatus, int totalSum,
                    List<Dish> orderedDishes, String addressLine) {
        this.orderedDateTime = orderedDateTime;
        this.orderStatus = orderStatus;
        this.totalSum = totalSum;
//        this.orderedDishes = orderedDishes;
        this.addressLine = addressLine;
    }


    public LocalDateTime getOrderedDateTime() {
        return orderedDateTime;
    }

    public void setOrderedDateTime(LocalDateTime orderedDateTime) {
        this.orderedDateTime = orderedDateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }
}
