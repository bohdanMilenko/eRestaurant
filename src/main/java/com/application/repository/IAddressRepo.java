package com.application.repository;

import com.application.entity.Address;
import com.application.entity.User;

import java.util.List;

public interface IAddressRepo {

    void addAddress(User user, Address address);

    Address getAddressById(int id);

    //TODO - IS GETTING ADDRESS User's scope or Address scope?
    List<Address> getAddressByUserId(int userId);

    boolean updateAddress(int userId, Address oldAddress, Address newAddress);

    boolean removeAddress(Address address, User user);

}
