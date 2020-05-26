package com.application.repository;

import com.application.entity.UserRole;

import java.util.Collection;

public interface IUserRoleRepo {

    void addRole(UserRole role);

    boolean remove(UserRole role);

    boolean update(UserRole role);

    Collection<UserRole> getAllRoles();

    Collection<UserRole> getUserRole(String roleName);
}
