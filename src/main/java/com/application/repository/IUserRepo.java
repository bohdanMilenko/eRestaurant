package com.application.repository;

import com.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepo extends JpaRepository<User, Integer> {

    User getByEmail(String email);

    List<User> getByNameContains(String name);

    @Query("SELECT " +
                "u " +
            "FROM " +
                " User u " +
            "WHERE " +
                " u.userRole.roleName like %:roleName")
    List<User> getUserByUserRole(String roleName);


    User getByUserIdAndPassword(int userId, String password);


}
