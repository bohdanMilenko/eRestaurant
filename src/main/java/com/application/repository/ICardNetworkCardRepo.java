package com.application.repository;

import com.application.entity.CardNetworkType;
import com.application.exception.RepoException;

public interface ICardNetworkCardRepo {

    void addCardType(CardNetworkType cardNetworkType) throws RepoException;

    CardNetworkTypeRepo getById(int id);

//    List<CardNetworkTypeRepo> getByNameLike(String nameLike);
//
//    List<CardNetworkTypeRepo> getAllCardNetworkTypes();
}
