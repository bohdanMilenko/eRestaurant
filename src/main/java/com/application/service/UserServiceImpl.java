package com.application.service;

import com.application.entity.User;
import com.application.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepo userRepo;

    public UserServiceImpl() {
    }

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return userRepo.getAllUsers();
    }
}
