package com.application.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "total_amount")
    private int totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany (mappedBy = "order")
    private List<Dish> orderedDishes;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Column(name = "ordered_time")
    private Timestamp orderedTime;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;



    public Order() {
    }

    public Order(int totalAmount, User user, List<Dish> orderedDishes,
                 OrderStatus orderStatus, Timestamp orderedTime, PaymentMethod paymentMethod, Address address) {
        this.totalAmount = totalAmount;
        this.user = user;
        this.orderedDishes = orderedDishes;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
        this.paymentMethod = paymentMethod;
        this.address = address;
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

    public Timestamp getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Timestamp orderedTime) {
        this.orderedTime = orderedTime;
    }

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<Dish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalAmount=" + totalAmount +
                ", user=" + user +
                ", orderedDishes=" + orderedDishes +
                ", orderStatus=" + orderStatus +
                ", orderedTime=" + orderedTime +
                '}';
    }
}
