package com.application.service;

import com.application.entity.Address;
import com.application.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IAddressService {

    void add(Address address) throws ServiceException;

    List<Address> getAllAddresses();

    Optional<Address> getAddressById(int id) throws ServiceException;

    List<Address> getAddressByUserId(int userId) throws ServiceException;

    Address updateAddress(int oldAddressId, Address newAddress) throws ServiceException;

    void removeAddress(Address address) throws ServiceException;

}
