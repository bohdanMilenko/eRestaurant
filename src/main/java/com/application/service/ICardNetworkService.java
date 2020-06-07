package com.application.service;

import com.application.entity.CardNetworkType;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ICardNetworkService {

    void addCardNetworkType(CardNetworkType cardNetworkType) throws ServiceException;

    Optional<CardNetworkType> getById(int id) throws ServiceException;

    CardNetworkType getByName(String name) throws ServiceException;

    List<CardNetworkType> getAllCardNetworkTypes();
}
