package com.application.repository;

import com.application.entity.PaymentMethod;
import com.application.exception.RepoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentMethodRepoImpl implements IPaymentMethodRepo {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void add(PaymentMethod paymentMethod) {

    }

    @Override
    public PaymentMethod getPaymentMethodById(int id) throws RepoException {
        return null;
    }

    @Override
    public PaymentMethod getPaymentMethodByUserIdAndCC(int userId, String ccNumber) throws RepoException {
        return null;
    }

    @Override
    public List<PaymentMethod> getPaymentMethodByUserId(int userId) {
        return null;
    }

    @Override
    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return false;
    }
}
