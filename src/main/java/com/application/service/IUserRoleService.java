package com.application.service;

import com.application.entity.UserRole;

import java.util.Collection;

public interface IUserRoleService {

    void add(UserRole userRole);

    Collection<UserRole> getUserRole(String roleName);


}
