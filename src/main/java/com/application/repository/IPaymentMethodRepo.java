package com.application.repository;

import com.application.entity.PaymentMethod;
import com.application.exception.RepoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentMethodRepo extends JpaRepository<PaymentMethod,Integer> {

    @Query("SELECT pm " +
            "FROM PaymentMethod pm " +
            "WHERE pm.user.userId = :userId AND  pm.ccNumber = :ccNumber")
    PaymentMethod getPaymentMethodByUserIdAndCC(@Param("userId") int userId,@Param("ccNumber") String ccNumber);

    @Query("SELECT " +
                "pm " +
            "FROM " +
                "PaymentMethod pm " +
            "WHERE " +
                "pm.user.userId = :userId")
    List<PaymentMethod> getPaymentMethodByUserId(@Param("userId") int userId);


}
