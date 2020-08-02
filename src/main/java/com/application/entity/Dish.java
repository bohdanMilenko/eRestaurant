package com.application.entity;

import com.application.util.jsonSerializer.DishSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "dish")
@Data
@JsonSerialize(using = DishSerializer.class)
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    @JsonProperty("dishId")
    private int dishId;

    @Column(name = "quantity")
    @JsonProperty("quantityOrdered")
    private int quantityOrdered;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    @JsonProperty("order")
    private Order order;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "dish_status_id")
    @JsonProperty("dishStatus")
    private DishStatus dishStatus;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    @JsonProperty("menuItem")
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "price_id")
    @JsonProperty("price")
    private Price price;

    public Dish() {
    }


}
