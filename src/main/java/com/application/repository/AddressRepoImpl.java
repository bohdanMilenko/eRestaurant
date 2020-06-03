package com.application.repository;

import com.application.entity.Address;
import com.application.exception.RepoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class AddressRepoImpl implements IAddressRepo {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(AddressRepoImpl.class);


    @Override
    public void addAddress(Address address) throws RepoException {
        try {
            em.persist(address);
        } catch (PersistenceException e) {
            logger.error("{} violated table constraints and caused: {}", address, e.toString());
            throw new RepoException(address.toString() + " failed to comply with the database constrains: "
                    + " .Cause: " + e.getCause(), e);
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a", Address.class);
        return query.getResultList();
    }

    @Override
    public Address getAddressById(int id) {
        return em.find(Address.class, id);
    }

    @Override
    public List<Address> getAddressByUserId(int userId) {
        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE a.user.userId = :userId", Address.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Address getByAddressAndUserId(int userId, String addressLine1) throws RepoException {
        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE a.user.userId = :userId AND a.addressLine1 = :addressLine1", Address.class);
        query.setParameter("userId", userId);
        query.setParameter("addressLine1", addressLine1);
        try {
            return query.getSingleResult();
        }catch (NoResultException e) {
            logger.error("Unable to find such record in table Address userId={}, addressLine={}, cause: {}", userId,addressLine1 ,e.toString());
            return null;
        } catch (NonUniqueResultException e) {
            logger.error("Multiple results found for passed: userId={}, addressLine={}, cause: {}", userId,addressLine1 , e.toString());
            throw new RepoException("Multiple results found for user : " + userId, e);
        }
    }

    @Override
    //TODO NOT SURE IF UPDATE LOGIC IS CORRECT
    public boolean updateAddress(int oldAddressId, Address updatedAddress) {
        Address oldAddress = getAddressById(oldAddressId);
        updatedAddress.setAddressId(oldAddressId);
        oldAddress = updatedAddress;
        logger.info("Successfully updated address from: oldAddressId = {}, to {}", oldAddressId ,updatedAddress );
        return true;
    }

    @Override
    public boolean removeAddress(Address address) {
        em.remove(address);
        return true;
    }
}
