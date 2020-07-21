package com.application.repository;

import com.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface IOrderRepo extends JpaRepository<Order, Integer> {

    @Query("SELECT " +
                "o " +
            "FROM " +
                "Order o " +
            "LEFT JOIN FETCH " +
                "o.orderedDishes " +
            "WHERE " +
                "o.user.userId = :userId")
    List<Order> getOrdersByUser_UserId(@Param("userId") int userId);


    @Query("SELECT " +
            "o " +
            "FROM " +
            "Order o " +
            "INNER JOIN FETCH " +
            "o.orderedDishes " +
            "WHERE " +
            "o.user.email = :email")
    List<Order> getOrdersByUser_UserEmail(@Param("email") String email);


    @Query("SELECT " +
            "o " +
            "FROM " +
            "Order o " +
            "INNER JOIN FETCH " +
            "o.orderedDishes " +
            "WHERE " +
            "o.user.userId = :userId")
    List<Order> getOrdersByUserId(@Param("userId") int userId);



    //TODO - RETURNS AS MANY ORDERS AS MANY DISHES INSIDE OF THE ORDER
    @Query("SELECT " +
                "o " +
            "FROM " +
                "Order o " +
            "LEFT JOIN FETCH " +
                "o.orderedDishes " +
            "WHERE " +
                "o.orderedTime >= :firstDate " +
            "AND " +
                "o.orderedTime <= :secondDate")
    List<Order> getOrdersByOrderedTimeBetween(@Param("firstDate")Timestamp firstDate,@Param("secondDate") Timestamp secondDate);

    List<Order> getOrdersByOrderStatus_OrderStatusName(String orderStatus);




}
