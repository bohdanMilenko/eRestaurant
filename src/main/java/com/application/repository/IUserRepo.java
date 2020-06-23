package com.application.repository;

import com.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepo extends JpaRepository<User, Integer> {

    User getByEmail(String email);

    List<User> getByNameContains(String name);

//    @Query("SELECT " +
//            "u " +
//            "FROM " +
//            "User u " +
//            "LEFT JOIN FETCH " +
//            "u.userRole.userRoleId " +
//            "WHERE " +
//            "u.userRole.roleName like %:roleName")
//    List<User> getUserByUserRoleName(@Param("roleName") String roleName);


    User getByUserIdAndPassword(int userId, String password);


}
