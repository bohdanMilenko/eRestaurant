package com.application.service;

import com.application.entity.Address;
import com.application.exception.ServiceException;

import java.util.List;

public interface IAddressService {

    void add(Address address) throws ServiceException;

    List<Address> getAllAddresses();

    Address getAddressById(int id) throws ServiceException;

    List<Address> getAddressByUserId(int userId) throws ServiceException;

    boolean updateAddress(int oldAddressId, Address newAddress) throws ServiceException;

    boolean removeAddress(Address address) throws ServiceException;

}
