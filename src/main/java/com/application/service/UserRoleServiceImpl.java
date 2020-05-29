package com.application.service;

import com.application.entity.UserRole;
import com.application.exception.RepoException;
import com.application.exception.ServiceException;
import com.application.repository.IUserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    IUserRoleRepo userRoleRepo;

    public UserRoleServiceImpl() {
    }

    public UserRoleServiceImpl(IUserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    @Transactional
    public void add(UserRole userRole) throws ServiceException {
       try{
           userRoleRepo.addRole(userRole);
       }catch (RepoException e){
           throw new ServiceException("Unable to add new role, validation failed", e);
       }
    }

    @Override
    public Collection<UserRole> getAllRoles() {
        return userRoleRepo.getAllRoles();
    }

    @Override
    public UserRole findByRoleName(UserRole userRole) throws ServiceException {
        //TODO - Validation for Nulls
        //I need to check here if the object is null and if the name is null, but do
        // I need validation on both Service and Repo levels?
        return userRoleRepo.findByRoleName(userRole.getRoleName());
    }

    @Override
    public Collection<UserRole> findByUserRoleNameLike(UserRole roleName) {
        //TODO - Validation for Nulls
        //I need to check here if the object is null and if the name is null, but do
        // I need validation on both Service and Repo levels?
        return userRoleRepo.findByUserRoleNameLike(roleName.getRoleName());
    }

    @Override
    public boolean updateName(UserRole role, UserRole newUserRole) throws ServiceException {
        try{
            userRoleRepo.updateName(role, newUserRole);
        }catch (RepoException e){
            //TODO do I need logging here as well as for the same method in Repo? which level of logging
            throw new ServiceException("Failed to update UserRole. Passed arguments failed validation:" +
                    "\nOld UserRole: " + role.toString() + "\nNew UserRole: " + newUserRole.toString(),e);
        }
        return false;
    }

    @Override
    public boolean remove(UserRole role) throws ServiceException {
        try {
            return userRoleRepo.remove(role);
        }catch (RepoException e){
            throw new ServiceException("Cannot remove user: " + role.toString(), e);
        }
    }
}