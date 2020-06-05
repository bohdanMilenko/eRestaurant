package com.application.service;

import com.application.entity.User;

import java.util.Collection;
import java.util.List;

public interface IUserService {

    void addUser(User user);

    User getById(int id);

    List<User> getAll();

    User getUserByEmail(String email);

    List<User> getUserByNameLike(String name);

    List<User> getUserByUserRole(int userRoleId);

    boolean authenticateUser(User user);

    boolean deactivateUser(User user);

}
