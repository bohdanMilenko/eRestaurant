package com.application.repository;

import com.application.entity.UserRole;
import com.application.exception.RepoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRoleRepo extends JpaRepository<UserRole, Integer> {


    UserRole getByRoleName(String roleName);

    List<UserRole> getAllRoles();

    List<UserRole> getByRoleNameContains(String roleName);



}
