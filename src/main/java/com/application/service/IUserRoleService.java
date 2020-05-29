package com.application.service;

import com.application.entity.UserRole;
import com.application.exception.ServiceException;

import java.util.List;

public interface IUserRoleService {

    void add(UserRole userRole) throws ServiceException;

    List<UserRole> getAllRoles();

    UserRole getByRoleName(UserRole userRole) throws ServiceException;

    List<UserRole> getByUserRoleNameLike(UserRole roleName) throws ServiceException;

    boolean updateName(UserRole role, UserRole newUserRole) throws ServiceException;

    boolean remove(UserRole role) throws ServiceException;






}
