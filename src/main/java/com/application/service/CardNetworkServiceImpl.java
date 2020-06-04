package com.application.service;

import com.application.entity.CardNetworkType;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import com.application.exception.ServiceException;
import com.application.repository.CardNetworkTypeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.application.util.PassedEntitiesValidator.*;

@Service
public class CardNetworkServiceImpl implements ICardNetworkService {

    @Autowired
    private CardNetworkTypeRepo cardNetworkTypeRepo;

    private static final Logger logger = LoggerFactory.getLogger(CardNetworkServiceImpl.class);

    public CardNetworkServiceImpl() {
    }

    public CardNetworkServiceImpl(CardNetworkTypeRepo cardNetworkTypeRepo) {
        this.cardNetworkTypeRepo = cardNetworkTypeRepo;
    }

    @Override
    public void addCardType(CardNetworkType cardNetworkType) throws ServiceException {
        try {
            validateObjectsForNull(cardNetworkTypeRepo);
            validateCardNetworkTypeFieldsForNulls(cardNetworkType);
            cardNetworkTypeRepo.addCardType(cardNetworkType);
        }catch (RepoException e) {
            logger.error("Repo threw exception while add( address = {}, and caused: {}", cardNetworkType, e.toString());
            throw new ServiceException("Repo failed to add new Address: " + cardNetworkType.toString(), e);
        }
    }

    @Override
    public CardNetworkTypeRepo getById(int id) throws ServiceException {
        try {
            validateObjectsForNull(id);
            return cardNetworkTypeRepo.getById(id);
        }catch (EntityValidationException e) {
            logger.error("Object failed validation for getById(id = {}))", id);
            throw new ServiceException("Passed entity failed validation: " + id, e);
        }
    }
}
