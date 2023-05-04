package utility;

import model.Application;

public class CheckFunctions
{

    public static boolean checkUsernameFormat(String username) {
        return username.matches(".*[^A-Za-z0-9_].*");
    }

    public static boolean checkUsernameExits(String username) {
        return Application.getUserByUsername(username) != null;
    }

    public static boolean checkPasswordFormat(String password) {
        if (!password.matches(".*[0-9].*") ) {
            return true;
        }
        if (!password.matches(".*[a-z].*")) {
            return true;
        }
        if (!password.matches(".*[A-Z].*")) {
            return true;
        }
        return !password.matches(".*[^A-Za-z0-9].*");
    }

    public static boolean checkReEnteredPassword(String password, String reEnteredPassword)
    {
        return true;
    }

    public static boolean checkEmailExits(String email)
    {
        return Application.getUserByEmail(email) != null;
    }

    public static boolean checkEmailFormat(String email) {
        return !email.matches("([^\\@\\s])+\\@([^\\.\\s])+\\.(\\S+)+");
    }




}
