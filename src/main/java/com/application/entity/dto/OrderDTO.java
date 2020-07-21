package com.application.entity.dto;

import com.application.entity.Dish;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {


    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDateTime orderedDateTime;
    private String orderStatus;
    @NotNull
    private int totalSum;
    private String addressLine;
//    private List<Dish> dishList;

    public OrderDTO() {
    }

    public OrderDTO(LocalDateTime orderedDateTime, String orderStatus, int totalSum,
                    String addressLine) {
        this.orderedDateTime = orderedDateTime;
        this.orderStatus = orderStatus;
        this.totalSum = totalSum;
        this.addressLine = addressLine;
    }

}
