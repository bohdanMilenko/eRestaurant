package com.application.repository;

import com.application.entity.User;

import java.util.List;

public interface IUserRepo {

    void addUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    List<User> getUserByNameLike(String name);

    List<User> getUserByUserRole(int userRoleId);

}
