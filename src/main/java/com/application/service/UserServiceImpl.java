package com.application.service;

import com.application.entity.User;
import com.application.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public void addUser(User user) {

    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepo.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUserByNameLike(String name) {
        return null;
    }

    @Override
    public List<User> getUserByUserRole(int userRoleId) {
        return null;
    }

    @Override
    public boolean authenticateUser(User user) {
        return false;
    }

    @Override
    public boolean deactivateUser(User user) {
        return false;
    }


}
