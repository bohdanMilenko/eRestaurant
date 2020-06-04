package com.application.repository;

import com.application.entity.CardNetworkType;
import com.application.exception.RepoException;

import java.util.List;

public interface ICardNetworkCardRepo {

    void addCardType(CardNetworkType cardNetworkType) throws RepoException;

    CardNetworkTypeRepo getById(int id);

    CardNetworkType getByName(String name);
//
//    List<CardNetworkTypeRepo> getAllCardNetworkTypes();
}
