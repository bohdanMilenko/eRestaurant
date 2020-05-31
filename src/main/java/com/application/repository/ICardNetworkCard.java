package com.application.repository;

import java.util.List;

public interface ICardNetworkCard {

    void addCardType(CardNetworkType cardNetworkType);

    CardNetworkType getById(int id);

    List<CardNetworkType> getByNameLike(String nameLike);

    List<CardNetworkType> getAllCardNetworkTypes();
}
