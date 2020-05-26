package com.application.service;

import com.application.entity.User;

import java.util.Collection;

public interface IUserService {

    User findById(int id);
    Collection<User> getAll();

}
