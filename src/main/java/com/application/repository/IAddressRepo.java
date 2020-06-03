package com.application.repository;

import com.application.entity.Address;
import com.application.exception.RepoException;

import java.util.List;

public interface IAddressRepo {

    void addAddress(Address address) throws RepoException;

    List<Address> getAllAddresses();

    Address getAddressById(int id);

    List<Address> getAddressByUserId(int userId);

    Address getByAddressAndUserId(int userId, String addressLine1) throws RepoException;

    boolean updateAddress(int oldAddressId, Address newAddress);

    boolean removeAddress(int userId, Address address);

}
