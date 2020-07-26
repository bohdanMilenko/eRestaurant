package com.application.service;

import com.application.entity.DishStatus;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IDishStatusService {

    void addDishStatus(DishStatus dishStatus) throws ServiceException;

    Optional<DishStatus> getById(int id) throws ServiceException;

    DishStatus getByName(DishStatus dishStatus) throws ServiceException;

    DishStatus getByName(String dishStatus) throws ServiceException;

    List<DishStatus> getByNameContains(String dishStatusLike) throws ServiceException;

    List<DishStatus> getAllDishStatuses();

    void remove(DishStatus dishStatus) throws ServiceException;
}
