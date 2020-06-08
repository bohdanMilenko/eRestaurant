package com.application.repository;

import com.application.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepo extends JpaRepository<UserRole, Integer> {


    UserRole getByRoleName(String roleName);

    List<UserRole> getByRoleNameContains(String roleName);

}
