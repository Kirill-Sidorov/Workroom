package org.sidorov.workroom.helper;

import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.logic.UserType;

public class UserAccessHelper {

    private UserAccessHelper() {
    }

    public static boolean checkUser(User user, UserType... userTypes) {
        boolean isAllowed = false;
        for (UserType type : userTypes) {
            if (user.getUserType() == type) {
                isAllowed = true;
                break;
            }
        }
        if ("locked".equals(user.getStatus())) {
            isAllowed = false;
        }
        return isAllowed;
    }

    public static boolean checkUser(User user, boolean isLockCheck, UserType... userTypes) {
        boolean isAllowed = false;
        for (UserType type : userTypes) {
            if (user.getUserType() == type) {
                isAllowed = true;
                break;
            }
        }
        if (isLockCheck) {
            if ("locked".equals(user.getStatus())) {
                isAllowed = false;
            }
        }
        return isAllowed;
    }
}
