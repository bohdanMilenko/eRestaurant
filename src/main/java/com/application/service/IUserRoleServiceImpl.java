package com.application.service;

import com.application.entity.UserRole;
import com.application.repository.IUserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class IUserRoleServiceImpl implements IUserRoleService {
    @Autowired
    IUserRoleRepo userRoleRepo;

    public IUserRoleServiceImpl() {
    }

    public IUserRoleServiceImpl(IUserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    @Transactional
    public void add(UserRole userRole) {
        userRoleRepo.addRole(userRole);
    }

    @Override
    public Collection<UserRole> getUserRole(String roleName) {
        return userRoleRepo.getUserRole(roleName);
    }
}
