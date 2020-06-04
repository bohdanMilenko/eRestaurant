package com.application.service;

import com.application.entity.PaymentMethod;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import com.application.exception.ServiceException;
import com.application.repository.IPaymentMethodRepo;
import com.application.util.CardNetworkTypeFactory;
import org.hibernate.action.internal.EntityActionVetoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.PassedEntitiesValidator.validatePaymentMethodForNulls;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {


    private IPaymentMethodRepo paymentMethodRepo;
    private ICardNetworkService cardNetworkService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);

    public PaymentMethodServiceImpl() {
    }

    @Autowired
    public PaymentMethodServiceImpl(IPaymentMethodRepo paymentMethod, ICardNetworkService cardNetworkService) {
        this.paymentMethodRepo = paymentMethod;
        this.cardNetworkService = cardNetworkService;
    }


    /**
     * Validation checks if the passed entity is null, if the fields that are "Not null" in DB are also not null
     * Additionally it checks if User and userId are not null
     * Based on the CC number it assigns an ID to the CardNetworkType. UserId and CardNetworkTypeId are mandatory
     */
    @Override
    public void add(PaymentMethod paymentMethod) throws ServiceException {
        logger.info("Starting writing to DB by using add(paymentMethod = {})", paymentMethod);
        try {
            validateObjectsForNull(paymentMethod);
            validatePaymentMethodForNulls(paymentMethod);
            String cardNetworkTypeName = CardNetworkTypeFactory.getType(paymentMethod.getCcNumber());
            paymentMethod.setCardNetworkType(cardNetworkService.getByName(cardNetworkTypeName));
            paymentMethodRepo.add(paymentMethod);
        } catch (EntityActionVetoException e) {
            logger.error("Object failed validation for add(paymentMethod = {}))", paymentMethod);
            throw new ServiceException("Validation for (nulls) in PaymentMethod failed: " + paymentMethod.toString(), e);
        }
    }

    @Override
    public PaymentMethod getPaymentMethodById(int id) throws ServiceException {
        try {
            validateObjectsForNull(id);
            return paymentMethodRepo.getPaymentMethodById(id);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getPaymentMethodById(id = {}))", id);
            throw new ServiceException("Validation for nulls failed: " + id, e);
        } catch (RepoException e) {
            logger.error("Unable to find getPaymentMethodById( id = {}), as it caused: {}", id, e.toString());
            throw new ServiceException("Repo failed to find one paymentMethod", e);
        }
    }

    @Override
    public PaymentMethod getPaymentMethodByUserIdAndCC(PaymentMethod paymentMethod) throws ServiceException {
        try {
            validateObjectsForNull(paymentMethod);
            validatePaymentMethodForNulls(paymentMethod);
            return paymentMethodRepo.getPaymentMethodByUserIdAndCC(paymentMethod.getUser().getUserId(), paymentMethod.getCcNumber());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getPaymentMethodByUserIdAndCC(paymentMethod = {}))", paymentMethod);
            throw new ServiceException("Validation for nulls failed: " + paymentMethod, e);
        } catch (RepoException e) {
            logger.error("Unable to find getPaymentMethodByUserIdAndCC( paymentMethod = {}), as it caused: {}", paymentMethod, e.toString());
            throw new ServiceException("Repo failed to find one paymentMethod", e);
        }
    }

    @Override
    public List<PaymentMethod> getPaymentMethodByUserId(int userId) throws ServiceException {
        try {
            validateObjectsForNull(userId);
            return paymentMethodRepo.getPaymentMethodByUserId(userId);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getPaymentMethodByUserId(userId = {}))", userId);
            throw new ServiceException("Validation for nulls failed: " + userId, e);
        }
    }

    @Override
    public boolean removePaymentMethod(PaymentMethod paymentMethod) throws ServiceException {
        try {
            validateObjectsForNull(paymentMethod);
            validatePaymentMethodForNulls(paymentMethod);
            return paymentMethodRepo.removePaymentMethod(paymentMethod);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for removePaymentMethod(paymentMethod = {}))", paymentMethod);
            throw new ServiceException("Validation for nulls failed: " + paymentMethod, e);
        }
    }
}
