package com.application.repository;

import com.application.entity.UserRole;
import com.application.exception.RepoException;

import java.util.List;

public interface IUserRoleRepo {

    void addRole(UserRole role) throws RepoException;

    UserRole getByRoleName(String roleName) throws RepoException;

    List<UserRole> getByUserRoleNameLike(String roleName) throws RepoException;

    List<UserRole> getAllRoles();

    boolean updateName(UserRole role, UserRole newUserRole) throws RepoException;

    boolean remove(UserRole role) throws RepoException;

}
