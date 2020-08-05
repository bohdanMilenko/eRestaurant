package com.application.repository;

import com.application.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDishRepo extends JpaRepository<Dish, Integer> {

    List<Dish> getDishesByDishStatus(DishStatus dishStatus);

    List<Dish> getDishesByOrder(Order order);

    List<Dish> getDishesByMenuItem(MenuItem menuItem);

    List<Dish> getDishesByOrderAndMenuItem(Order order, MenuItem menuItem);

//    @Query("SELECT " +
//            "new com.application.entity.dto.PopularDishReport(d.menuItem.menuItemName, COUNT (d.dishId), SUM (d.price.priceValue)) " +
//            "FROM " +
//            "Dish d " +
//            "GROUP BY d.menuItem.menuItemName")
//    List<PopularDishReport> getSalesByItemMenuAllTime();

    @Query("SELECT " +
            "d " +
            "FROM Dish AS d " +
            "LEFT JOIN " +
            "d.menuItem " +
            "WHERE " +
            "d.menuItem.menuCategory IN :menuCategory " +
            "AND d.dishStatus = :dishStatus")
    List<Dish> getDishesByMenuItemCategoryAndDishStatus(@Param("menuCategory") List<MenuCategory> menuCategory, @Param("dishStatus") DishStatus dishStatus);


//    void removeDishByOrderAndMenuItem(Order order, MenuItem menuItem);

}
