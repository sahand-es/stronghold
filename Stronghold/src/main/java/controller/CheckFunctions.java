package controller;

import model.Application;

public class CheckFunctions
{

    public static boolean checkEmpty(String username, String password, String nickname, String email)
    {
        return true;
    }

    public static boolean checkUsernameFormat(String username) {
        if (username.matches(".*[^A-Za-z0-9_].*"))
            return true;
        else
            return false;
    }

    public static boolean checkUsernameExits(String username) {
        if (Application.getUserByUsername(username) != null)
            return true;
        else
            return false;
    }

    public static boolean checkPasswordFormat(String password) {
        if (!password.matches(".*[0-9].*") ) {
            return true;
        }
        if (!password.matches(".*[a-z].*")) {
            return true;
        }
        if (!password.matches(".*[^A-Za-z0-9].*")){
            return true;
        }
        return false;
    }

    public static boolean checkReEnteredPassword(String password, String reEnteredPassword)
    {
        return true;
    }

    public static boolean emailExits(String email)
    {
        return true;
    }

    public static boolean checkEmailFormat(String email)
    {
        return true;
    }




}
