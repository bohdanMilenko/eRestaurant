package com.application.repository;

import com.application.entity.Order;
import com.application.entity.dto.ReportingOrdersDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
            "LEFT JOIN FETCH " +
            "o.orderedDishes " +
            "WHERE " +
            "o.user.userId = :userId " +
            "AND " +
            "o.orderId = :orderId")
    Order getOrderByOrderAndUserId(@Param("userId") int userId, @Param("orderId") int orderId);


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


    //TODO - switched from left join to inner
    @Query("SELECT " +
            "o " +
            "FROM " +
            "Order o " +
            "INNER JOIN FETCH " +
            "o.orderedDishes " +
            "WHERE " +
            "o.orderedTime >= :firstDate " +
            "AND " +
            "o.orderedTime <= :secondDate")
    List<Order> getOrdersByOrderedTimeBetween(@Param("firstDate") Timestamp firstDate, @Param("secondDate") Timestamp secondDate);

    List<Order> getOrdersByOrderStatus_OrderStatusName(String orderStatus);


    @Query("SELECT " +
            "new  com.application.entity.dto.ReportingOrdersDTO(o.orderedTime, COUNT (o.orderId), sum(o.totalAmount)) " +
            "FROM " +
            "Order o " +
            "WHERE " +
            "o.orderedTime >= :firstDate " +
            "AND " +
            "o.orderedTime <= :secondDate " +
            "GROUP BY DATE(o.orderedTime) "
    )
    List<ReportingOrdersDTO> getOrdersReportWithinDates(@Param("firstDate") LocalDateTime firstDate, @Param("secondDate") LocalDateTime secondDate,
                                                        Sort sort);


}
