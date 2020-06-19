package com.application.repository;

import com.application.entity.DishStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IDishStatusRepo extends JpaRepository<DishStatus, Integer> {

    DishStatus getDishStatusByDishStatusName(String dishStatus);

    List<DishStatus> getDishStatusesByDishStatusNameContains(String string);


}
