package com.application.repository;

import com.application.entity.CardNetworkType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardNetworkTypeRepo extends JpaRepository<CardNetworkType, Integer> {

    CardNetworkType getByCardProviderName(String name);

}
