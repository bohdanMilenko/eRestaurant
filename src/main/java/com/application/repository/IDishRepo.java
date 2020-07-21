package com.application.repository;

import com.application.entity.Dish;
import com.application.entity.DishStatus;
import com.application.entity.MenuItem;
import com.application.entity.Order;
import com.application.entity.dto.PopularDishReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDishRepo extends JpaRepository<Dish, Integer> {

    List<Dish> getDishesByDishStatus(DishStatus dishStatus);

    List<Dish> getDishesByOrder(Order order);

    List<Dish> getDishesByMenuItem(MenuItem menuItem);

    List<Dish> getDishesByOrderAndMenuItem(Order order, MenuItem menuItem);

    @Query("SELECT " +
            "new com.application.entity.dto.PopularDishReport(d.menuItem.menuItemName, COUNT (d.dishId), SUM (d.price.priceValue)) " +
            "FROM " +
            "Dish d " +
            "GROUP BY d.menuItem.menuItemName")
    List<PopularDishReport> getSalesByItemMenuAllTime();


//    void removeDishByOrderAndMenuItem(Order order, MenuItem menuItem);

}
