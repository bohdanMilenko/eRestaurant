package com.application.repository;

import com.application.entity.User;

import java.util.Collection;

public interface IUserRepo {

    User findUserById(int id);
    Collection<User> findUserByName(String name);
    Collection<User> getAllUsers();
    void save(User user);

}
