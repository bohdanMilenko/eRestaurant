package com.application.service;

import com.application.entity.CardNetworkType;
import com.application.exception.ServiceException;
import com.application.repository.CardNetworkTypeRepo;

public interface ICardNetworkService {

    void addCardType(CardNetworkType cardNetworkType) throws ServiceException;

    CardNetworkTypeRepo getById(int id) throws ServiceException;
}
