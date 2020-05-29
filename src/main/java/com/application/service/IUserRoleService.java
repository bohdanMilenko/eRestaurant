package com.application.service;

import com.application.entity.UserRole;
import com.application.exception.ServiceException;

import java.util.Collection;

public interface IUserRoleService {

    void add(UserRole userRole) throws ServiceException;

    Collection<UserRole> getAllRoles();

    UserRole findByRoleName(UserRole userRole) throws ServiceException;

    Collection<UserRole> findByUserRoleNameLike(UserRole roleName);

    boolean updateName(UserRole role, UserRole newUserRole) throws ServiceException;

    boolean remove(UserRole role) throws ServiceException;






}
