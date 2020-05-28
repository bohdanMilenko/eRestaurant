package com.application.util;

import com.application.entity.UserRole;
import com.application.exception.EntityValidationException;

public class PassedEntitiesValidator {

    public static void validateObjectsForNull(Object o1) throws EntityValidationException {
        if (o1 == null) {
            throw new EntityValidationException("Object is null");
        }
    }

    public static void validateObjectsForNull(Object o1, Object o2) throws EntityValidationException {
        if (o1 == null || o2 == null) {
            throw new EntityValidationException("Objects are null");
        }
    }

    public static void validateObjectsForNull(Object o1, Object o2, Object o3) throws EntityValidationException {
        if (o1 == null || o2 == null || o3 == null) {
            throw new EntityValidationException("Objects are null");
        }
    }

    public static void validateUserRoleFieldsForNulls(UserRole o1) throws EntityValidationException {
        if (o1.getRoleName() == null) {
            throw new EntityValidationException("UserRole name is null: " + o1.toString());
        }
    }


}
