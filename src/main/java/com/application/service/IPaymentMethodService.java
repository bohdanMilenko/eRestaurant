package com.application.service;

import com.application.entity.PaymentMethod;
import com.application.exception.ServiceException;

import java.util.List;

public interface IPaymentMethodService {

    void add(PaymentMethod paymentMethod) throws ServiceException;

    PaymentMethod getPaymentMethodById(int id) throws ServiceException;

    PaymentMethod getPaymentMethodByUserIdAndCC(PaymentMethod paymentMethod) throws ServiceException;

    List<PaymentMethod> getPaymentMethodByUserId(int userId) throws ServiceException;

    boolean removePaymentMethod(PaymentMethod paymentMethod) throws ServiceException;
}
