package com.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
@Data
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private int orderStatusId;
    @Column(name = "order_status_name")
    private String orderStatusName;

    public OrderStatus() {
    }

    public OrderStatus(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }


}
