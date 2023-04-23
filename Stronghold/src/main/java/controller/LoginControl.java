package controller;

import model.Application;
import model.User;
import view.enums.messages.LoginMessages;

public class LoginControl {
    public static LoginMessages checkLogin(String username, String password){
        User user;

        if (username == null)
            return LoginMessages.EMPTY_USERNAME;

        if (password == null)
            return LoginMessages.EMPTY_PASSWORD;

        user = Application.getUserByUsername(username);

        if (user == null)
            return LoginMessages.USERNAME_DIDNT_MATCH;

        if (!user.checkPassword(password))
            return LoginMessages.PASSWORD_DIDNT_MATCH;




        return LoginMessages.SUCCESSFUL;
    }

    public static LoginMessages checkForgotPassword(String answer){
        return null;
    }
}
