package com.application.repository;

import com.application.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPriceRepo extends JpaRepository<Price, Integer> {

    @Query("SELECT " +
            "pr " +
            "FROM " +
            "Price pr " +
            "WHERE " +
            "pr.menuItem.menuItemId= :menuItemID")
    Price getPriceByMenuItem(@Param("menuItemID") int menuItemID);
}
