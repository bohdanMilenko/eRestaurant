package com.application.util;

import com.application.entity.*;
import com.application.exception.EntityValidationException;

public class PassedEntitiesValidator {

    public static void validateObjectsForNull(Object o1) throws EntityValidationException {
        if (o1 == null) {
            throw new EntityValidationException("Passed object is null");
        }
    }

    public static void validateObjectsForNull(Object o1, Object o2) throws EntityValidationException {
        if (o1 == null || o2 == null) {
            throw new EntityValidationException("Passed object is null, o1 is: " + o1 + " o2 is: " + o2);
        }
    }

    public static void validateObjectsForNull(Object o1, Object o2, Object o3) throws EntityValidationException {
        if (o1 == null || o2 == null || o3 == null) {
            throw new EntityValidationException("Passed object is null, o1 is: " + o1 + " o2 is: " + o2+ " o3 is: " + o3);
        }
    }

    public static void validateUserRoleFieldsForNulls(UserRole o1) throws EntityValidationException {
        if (o1.getRoleName() == null) {
            throw new EntityValidationException("UserRole name is null:: " + o1.toString());
        }
    }
    public static void validateUserRoleFieldsForNulls(UserRole o1, UserRole o2) throws EntityValidationException {
        if (o1.getRoleName() == null || o2.getRoleName()==null) {
            throw new EntityValidationException("UserRole name is nulls that violate table constrains \no1: " + o1.toString() + "\no2: " + o2.toString());
        }
    }

    public static void validateProvinceFieldsForNulls(Province o1) throws EntityValidationException {
        if (o1.getFullNameProvince() == null || o1.getAbbreviationProvince()==null) {
            throw new EntityValidationException("Province fields contain nulls that violate table constrains: " + o1.toString());
        }
    }

    public static void validateProvinceFieldsForNulls(Province o1, Province o2) throws EntityValidationException {
        if (o1.getFullNameProvince() == null || o1.getAbbreviationProvince()==null) {
            throw new EntityValidationException("Province fields contain nulls that violate table constrains \no1: " + o1.toString() +
                    "\no2 :" + o2.toString());
        }
    }

    public static void validateAddressFieldsForNulls(Address o1) throws EntityValidationException {
        if (o1.getAddressLine1() == null || o1.getCity()==null || o1.getCountry()==null
                || o1.getPostalCode()==null) {
            throw new EntityValidationException("Address fields contain nulls that violate table constrains: " + o1.toString());
        }
        if (o1.getUser().getUserId() == 0){
            throw new EntityValidationException("Address didn't contain userId: " + o1.getUser().getUserId());
        }
    }

    public static void validateUserFieldsForNulls(User user) throws EntityValidationException{
        if(user.getEmail() == null || user.getPhoneNumber() == null ||
                user.getAccountCreationDateTime() == null || user.getUserRole()==null){
            throw new EntityValidationException("User fields contain nulls that violate table constrains: " + user.toString());
        }
    }

    public static void validateCardNetworkTypeFieldsForNulls(CardNetworkType cardNetworkType) throws EntityValidationException{
        if(cardNetworkType.getCardProviderName() == null){
            throw new EntityValidationException("CardNetworkName is null");
        }
    }


}
