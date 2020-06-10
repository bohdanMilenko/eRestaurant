package com.application.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
    @Column(name = "ordered_time")
    private LocalDateTime orderedTime;

    public Order() {
    }

    public Order(User user, OrderStatus orderStatus, LocalDateTime orderedTime) {
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int order_id) {
        this.orderId = order_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", user=" + user +
                ", orderStatus=" + orderStatus +
                ", orderedTime=" + orderedTime +
                '}';
    }
}
