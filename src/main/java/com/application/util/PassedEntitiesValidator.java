package com.application.util;

import com.application.entity.*;
import com.application.exception.EntityValidationException;
import org.jetbrains.annotations.NotNull;

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
            throw new EntityValidationException("UserRole name is null: " + o1.toString());
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
        try{
            validateObjectsForNull(o1.getUser());
        }catch (EntityValidationException e){
            throw new EntityValidationException("User inside Address was null");
        }
        if (o1.getUser().getUserId() == 0){
            throw new EntityValidationException("Address didn't contain userId: " + o1.toString());
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
            throw new EntityValidationException("CardNetwork Name is null");
        }
    }

    public static void validateDishStatusFieldsForNulls(@NotNull DishStatus dishStatus) throws EntityValidationException{
        if(dishStatus.getDishStatusName() == null){
            throw new EntityValidationException("DishStatus name is null");
        }
    }

    public static void validatePaymentMethodForNulls(PaymentMethod paymentMethod) throws EntityValidationException{
        if(paymentMethod.getCcIssueDate() == null || paymentMethod.getCcNumber()== null ||
                paymentMethod.getCcv2() == null || paymentMethod.getNameOnCard()==null ||
                    paymentMethod.getPaymentType()==null){
            throw new EntityValidationException("Payment specific fields contain nulls that violate table constrains: " + paymentMethod.toString());
        }
        try{
            validateObjectsForNull(paymentMethod.getUser());
            validateUserFieldsForNulls(paymentMethod.getUser());
        }catch (EntityValidationException e){
            throw new EntityValidationException("User inside Payment was null: " + paymentMethod.toString());
        }
    }

    public static void validateDishForNulls(Dish dish) throws EntityValidationException{
        if(dish.getQuantityOrdered() == 0 ){
            throw new EntityValidationException("Dish doesn't contain quantity ordered: " + dish.toString());
        }
        try{
            validateObjectsForNull(dish.getDishStatus());
            validateObjectsForNull(dish.getDishStatus().getDishStatusId());
            validateObjectsForNull(dish.getMenuItem());
            validateObjectsForNull(dish.getMenuItem().getMenuItemId());
            validateObjectsForNull(dish.getOrder());
            validateObjectsForNull(dish.getOrder().getOrderId());
            validateObjectsForNull(dish.getPrice());
            validateObjectsForNull(dish.getPrice().getPriceId());
        }catch (EntityValidationException e){
            throw new EntityValidationException("Entity inside of Dish was null & violated table constraints: " + dish.toString());
        }
    }

    public static void validateMenuCategoryForNulls(MenuCategory menuCategory) throws EntityValidationException {
        if(menuCategory.getCategoryName()==null){
            throw new EntityValidationException("MenuItemCategory doesn't contain name: " + menuCategory.toString());
        }
    }

    public static void validatePriceFieldsForNulls(Price price) {
    }

    public static void validateOrderFieldsForNulls(Order order) {
    }

    public static void validateMenuItemFieldsForNulls(MenuItem menuItem) {
    }


}
