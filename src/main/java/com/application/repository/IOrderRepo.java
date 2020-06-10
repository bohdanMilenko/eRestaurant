package com.application.repository;

import com.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface IOrderRepo extends JpaRepository<Order, Integer> {




    //TODO - Finish Report
    @Query("SELECT " +
                "o " +
            "FROM " +
                " Order o ")
            int getTotalSumByOrderAndDate(LocalDate dateOfOrder);

    int getTotalSumByOrderAndDate(LocalDate dateOfOrderBeginning, LocalDate dateOfOrderEnd);
}
