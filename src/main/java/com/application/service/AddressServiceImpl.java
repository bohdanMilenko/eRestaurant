package com.application.service;

import com.application.entity.Address;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import com.application.exception.ServiceException;
import com.application.repository.IAddressRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.application.util.PassedEntitiesValidator.validateAddressFieldsForNulls;
import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressRepo addressRepo;

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    public AddressServiceImpl() {
    }

    public AddressServiceImpl(IAddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    @Transactional
    public void add(Address address) throws ServiceException {
        logger.info("Starting writing to DB by using add(address = {})", address);
        try {
            validateObjectsForNull(address);
            validateAddressFieldsForNulls(address);
            validateObjectsForNull(address.getUser());
            validateObjectsForNull(address.getUser().getUserId());
            if (addressRepo.getByAddressAndUserId(address.getUser().getUserId(), address.getAddressLine1()) == null) {
                addressRepo.addAddress(address);
            } else {
                logger.error("Unable to execute add (address = {}), duplicated address", address);
                throw new ServiceException("Attempt to add duplicated address for user: " + address.getUser().getUserId());
            }

        } catch (EntityValidationException e) {
            logger.error("Object failed validation for add(address = {}))", address);
            throw new ServiceException("Passed entity failed validation: " + address, e);
        }catch (RepoException e) {
            logger.error("Repo threw exception while add( address = {}, and caused: {}", address, e.toString());
            throw new ServiceException("Repo failed to add new Address: " + address.toString(), e);
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepo.getAllAddresses();
    }

    @Override
    public Address getAddressById(int id) throws ServiceException {
        try {
            validateObjectsForNull(id);
            return addressRepo.getAddressById(id);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getAddressById(id = {}))", id);
            throw new ServiceException("Passed entity failed validation: " + id, e);
        }
    }

    @Override
    public List<Address> getAddressByUserId(int userId) throws ServiceException {
        try {
            validateObjectsForNull(userId);
            return addressRepo.getAddressByUserId(userId);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getAddressByUserId(id = {}))", userId);
            throw new ServiceException("Passed entity failed validation: " + userId, e);
        }
    }

    @Override
    @Transactional
    public boolean updateAddress(int oldAddressId, Address updatedAddress) throws ServiceException {
        logger.info("Entering updateAddress( oldAddressId = {}, updatedAddress = {} )", oldAddressId, updatedAddress);
        try {
            validateObjectsForNull(oldAddressId);
            validateObjectsForNull(updatedAddress);
            validateAddressFieldsForNulls(updatedAddress);
            return addressRepo.updateAddress(oldAddressId, updatedAddress);
        } catch (EntityValidationException e) {
            logger.error("Objects failed validation for updateAddress(oldAddressId = {}," +
                    " updatedAddress = {}), cause: {} ", oldAddressId, updatedAddress, e.toString());
            throw new ServiceException("Passed entity failed validation, oldAddressId:" + oldAddressId + " updatedAddress=" + updatedAddress, e);
        }
    }

    @Override
    @Transactional
    public boolean removeAddress(Address address) throws ServiceException {
        try {
            validateObjectsForNull(address);
            validateAddressFieldsForNulls(address);
            return addressRepo.removeAddress(address);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getAddressByUserId(id = {}))", address);
            throw new ServiceException("Passed entity failed validation: " + address, e);
        }
    }
}
