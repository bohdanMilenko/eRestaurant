package com.application.entity.dto;

import java.time.LocalDateTime;

public class OrdersReport {

    private LocalDateTime orderDateTime;
    private int dishesOrderedQuantity;
    private int orderSum;
    private int orderId;

    public OrdersReport() {
    }

    public OrdersReport(LocalDateTime orderDateTime, int dishesOrderedQuantity, int orderSum, int orderId) {
        this.orderDateTime = orderDateTime;
        this.dishesOrderedQuantity = dishesOrderedQuantity;
        this.orderSum = orderSum;
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public int getDishesOrderedQuantity() {
        return dishesOrderedQuantity;
    }

    public void setDishesOrderedQuantity(int dishesOrderedQuantity) {
        this.dishesOrderedQuantity = dishesOrderedQuantity;
    }

    public int getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(int orderSum) {
        this.orderSum = orderSum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "SalesReport{" +
                "orderDateTime=" + orderDateTime +
                ", dishesOrderedQuantity=" + dishesOrderedQuantity +
                ", orderSum=" + orderSum +
                ", orderId=" + orderId +
                '}';
    }
}
