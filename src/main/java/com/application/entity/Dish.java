package com.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dish")
@Data
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private int dishId;
    @Column(name = "quantity")
    private int quantityOrdered;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
    @ManyToOne
    @JoinColumn(name = "dish_status_id")
    private DishStatus dishStatus;
    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;
    @ManyToOne
    @JoinColumn(name = "price_id")
    private Price price;

    public Dish() {
    }


}
