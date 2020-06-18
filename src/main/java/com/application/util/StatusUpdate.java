package com.application.util;

import com.application.entity.DishStatus;
import com.application.entity.OrderStatus;

public class StatusUpdate {

    public static void updateDishStatus(DishStatus dishStatus) {
        switch (dishStatus.getDishStatusName()) {
            case "Waiting" -> dishStatus.setDishStatusName("Pending");
            case "Pending" -> dishStatus.setDishStatusName("Ready for Delivery");
            case "Ready for Delivery" -> dishStatus.setDishStatusName("Sent Out");
            default -> dishStatus.setDishStatusName("Waiting");
        }
        ;
    }

    public static void updateOrderStatus(OrderStatus orderStatus) {
        switch (orderStatus.getOrderStatusName()) {
            case "Pending" -> orderStatus.setOrderStatusName("Ready for Delivery");
            case "Ready for Delivery" -> orderStatus.setOrderStatusName("Delivering");
            case "Delivering" -> orderStatus.setOrderStatusName("Delivered");
            default -> orderStatus.setOrderStatusName("Pending");
        }
        ;
    }

}
