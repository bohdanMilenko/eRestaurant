package com.application.repository;

import com.application.entity.UserRole;
import com.application.exception.RepoException;

import java.util.Collection;

public interface IUserRoleRepo {

    void addRole(UserRole role) throws RepoException;

    UserRole findByRoleName(String roleName) throws RepoException;

    Collection<UserRole> findByUserRoleNameLike(String roleName);

    boolean remove(UserRole role);

    boolean update(UserRole role);

    Collection<UserRole> getAllRoles();

    Collection<UserRole> getUserRole(String roleName);
}
