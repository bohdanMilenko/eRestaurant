package com.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
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

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "orderStatusId=" + orderStatusId +
                ", orderStatusName=" + orderStatusName +
                '}';
    }
}
