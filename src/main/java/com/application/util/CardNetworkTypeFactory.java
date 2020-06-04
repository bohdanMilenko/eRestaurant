package com.application.util;

import com.application.entity.CardNetworkType;
import com.application.exception.EntityValidationException;

public class CardNetworkTypeFactory {

    public static String getType(String ccNumber) throws EntityValidationException {
        return switch (ccNumber.charAt(0)) {
            case 3 -> "American Express";
            case 4 -> "Visa";
            case 5 -> "Master Card";
            default -> throw new EntityValidationException("CC number is not valid");
        };
    }
}
