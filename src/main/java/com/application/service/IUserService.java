package com.application.service;

import com.application.entity.User;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void addUser(User user) throws ServiceException;

    Optional<User> getById(int id) throws ServiceException;

    List<User> getAll();

    User getUserByEmail(String email) throws ServiceException;

    List<User> getUserByNameLike(String name) throws ServiceException;

    List<User> getUserByUserRoleName(String userRoleName) throws ServiceException;

    User authenticateUser(User user) throws ServiceException;

    void deactivateUser(User user) throws ServiceException;

}
