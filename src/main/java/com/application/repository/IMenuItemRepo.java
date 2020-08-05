package com.application.repository;

import com.application.entity.MenuItem;
import com.application.entity.dto.ReportingMenuItemDTO;
import com.application.entity.dto.ReportingOrdersDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface IMenuItemRepo extends JpaRepository<MenuItem,Integer> {

    MenuItem getMenuItemByMenuItemName(String menuItemName);

    List<MenuItem> getMenuItemByMenuCategory_MenuItemCategoryId(int menuCategoryId);

    @Query("SELECT " +
            "new  com.application.entity.dto.ReportingMenuItemDTO(d.menuItem.menuItemName,  COUNT (d.dishId), sum(d.quantityOrdered * d.price.priceValue)) " +
            "FROM " +
            "Dish d " +
            "where " +
            "d.order.orderedTime >= :firstDate " +
            "AND " +
            "d.order.orderedTime <= :secondDate " +
            "GROUP BY d.menuItem.menuItemName "
    )
    List<ReportingMenuItemDTO> getSalesByMenuItem(@Param("firstDate") Timestamp firstDate, @Param("secondDate") Timestamp secondDate);

}
