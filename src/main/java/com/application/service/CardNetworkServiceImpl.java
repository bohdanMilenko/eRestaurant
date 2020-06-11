package com.application.service;

import com.application.entity.CardNetworkType;
import com.application.exception.EntityValidationException;
import com.application.exception.ServiceException;
import com.application.repository.ICardNetworkTypeRepo;
import org.hibernate.action.internal.EntityActionVetoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.application.util.PassedEntitiesValidator.validateCardNetworkTypeFieldsForNulls;
import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;

@Service
public class CardNetworkServiceImpl implements ICardNetworkService {

    private ICardNetworkTypeRepo cardNetworkTypeRepo;
    private static final Logger logger = LoggerFactory.getLogger(CardNetworkServiceImpl.class);

    public CardNetworkServiceImpl() {
    }

    @Autowired
    public CardNetworkServiceImpl(ICardNetworkTypeRepo cardNetworkTypeRepo) {
        this.cardNetworkTypeRepo = cardNetworkTypeRepo;
    }


    @Override
    public void addCardNetworkType(CardNetworkType cardNetworkType) throws ServiceException {
        try {
            validateObjectsForNull(cardNetworkTypeRepo);
            validateCardNetworkTypeFieldsForNulls(cardNetworkType);
            if (getByName(cardNetworkType.getCardProviderName()) == null) {
                cardNetworkTypeRepo.save(cardNetworkType);
            } else {
                logger.error("Unable to execute addCardNetworkType (cardNetworkType = {}), duplicated cardNetworkType", cardNetworkType);
                throw new ServiceException("Attempt to add duplicated cardNetworkType: " + cardNetworkType.getCardProviderName());
            }
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for addCardNetworkType(cardNetworkType = {}))", cardNetworkType);
            throw new ServiceException("Validation for (nulls) in cardNetworkType failed: " + cardNetworkType, e);
        }
    }

    @Override
    public List<CardNetworkType> getAllCardNetworkTypes() {
        return cardNetworkTypeRepo.findAll();
    }

    @Override
    public Optional<CardNetworkType> getById(int id) throws ServiceException {
        try {
            validateObjectsForNull(id);
            return cardNetworkTypeRepo.findById(id);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getById(id = {}))", id);
            throw new ServiceException("Passed entity failed validation: " + id, e);
        }
    }

    @Override
    public CardNetworkType getByName(String name) throws ServiceException {
        try {
            validateObjectsForNull(name);
            return cardNetworkTypeRepo.getByCardProviderName(name);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getByName(name = {}))", name);
            throw new ServiceException("Passed entity failed validation: " + name, e);
        }
    }
}
