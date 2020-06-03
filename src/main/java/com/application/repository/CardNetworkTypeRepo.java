package com.application.repository;

import com.application.entity.CardNetworkType;
import com.application.exception.RepoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Repository
public class CardNetworkTypeRepo implements ICardNetworkCardRepo {

    @PersistenceContext
    private EntityManager em;

    private final static Logger logger = LoggerFactory.getLogger(CardNetworkTypeRepo.class);

    @Override
    @Transactional
    public void addCardType(CardNetworkType cardNetworkType) throws RepoException {
        try {
            em.persist(cardNetworkType);
        }catch (PersistenceException e) {
            logger.error("{} violated table constraints and caused: {}", cardNetworkType, e.toString());
            throw new RepoException("Failed to comply with the database constrains: " + cardNetworkType.toString()
                    + " .Cause " + e.getCause(), e);
        }
    }

    @Override
    public CardNetworkTypeRepo getById(int id) {
        return em.find(CardNetworkTypeRepo.class, id);
    }

//    @Override
//    public List<CardNetworkTypeRepo> getByNameLike(String nameLike) {
//        return null;
//    }
//
//    @Override
//    public List<CardNetworkTypeRepo> getAllCardNetworkTypes() {
//        return null;
//    }
}
