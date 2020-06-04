package com.application.repository;

import com.application.entity.PaymentMethod;
import com.application.exception.RepoException;

import java.util.List;

public interface IPaymentMethodRepo {

    void add(PaymentMethod paymentMethod);

    PaymentMethod getPaymentMethodById(int id) throws RepoException;

    PaymentMethod getPaymentMethodByUserIdAndCC(int userId, String ccNumber) throws RepoException;

    List<PaymentMethod> getPaymentMethodByUserId(int userId);

    boolean removePaymentMethod(PaymentMethod paymentMethod);
}
