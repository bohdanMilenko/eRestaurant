package com.application.repository;

import com.application.entity.Address;
import com.application.entity.User;
import com.application.exception.RepoException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IAddressRepo extends JpaRepository<Address, Integer> {


    List<Address> getAll();
    @Query("SELECT a " +
    "FROM Address a " +
            "WHERE a.user.userId = :userId")
    List<Address> getAddressesByUserAndSort(@Param("userId") int userId, Sort sort);

    List<Address> getAddressByAddressIdAndUser(int addressId, User user);

    @Query("SELECT " +
            "a FROM Address a " +
            "WHERE a.user.userId = :userId AND a.addressLine1 = :addressLine1")
    Address getByUserIdAndAddressLine1 (@Param("userId") int userId,@Param("addressLine1")  String addressLine1);


}
