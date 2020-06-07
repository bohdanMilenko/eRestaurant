package com.application.service;

import com.application.entity.PaymentMethod;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IPaymentMethodService {

    void add(PaymentMethod paymentMethod) throws ServiceException;

    List<PaymentMethod> getPaymentMethodByUserId(int userId) throws ServiceException;

    Optional<PaymentMethod> getPaymentMethodById(int id) throws ServiceException;

    PaymentMethod getPaymentMethodByUserIdAndCC(PaymentMethod paymentMethod) throws ServiceException;

    void removePaymentMethod(PaymentMethod paymentMethod) throws ServiceException;
}
