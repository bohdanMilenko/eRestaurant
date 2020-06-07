package com.application.repository;

import com.application.entity.CardNetworkType;
import com.application.exception.RepoException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICardNetworkTypeRepo extends JpaRepository<CardNetworkType,Integer> {

    CardNetworkType getByCardProviderName(String name);

}
