package controller;

import model.Application;
import model.User;
import utility.CheckFunctions;
import utility.SecurityQuestions;
import view.enums.messages.LoginMessages;

public class LoginControl {

    private static User currentUser;
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

        Application.setCurrentUser(user);

        return LoginMessages.SUCCESSFUL;
    }

    public static LoginMessages checkForgotPassword(String username){
        if (username == null)
            return LoginMessages.EMPTY_USERNAME;

        User user = Application.getUserByUsername(username);

        if (user == null)
            return LoginMessages.USERNAME_DIDNT_MATCH;

        currentUser = user;

        return LoginMessages.SUCCESSFUL;
    }

    public static void clearCurrentUser(){
        currentUser = null;
    }

    public static String getSecurityQuestion(){
        return SecurityQuestions.getSecurityQuestion(currentUser.getSecurityQuestionNumber());
    }

    public static LoginMessages checkAskSecurityQuestion(String input){
        if (input.equals("back"))
            return LoginMessages.BACK;

        if (!currentUser.checkAnswer(input))
            return LoginMessages.INCORRECT_ANSWER;

        return LoginMessages.SUCCESSFUL;
    }

    public static LoginMessages checkGetPassword(String password){
        if (password.equals("back"))
            return LoginMessages.BACK;

        if (password.equals("random"))
            return LoginMessages.RANDOM;

        if (password.equals(""))
            return LoginMessages.EMPTY_PASSWORD;

        if (password.length() < 6)
            return LoginMessages.INSUFFICIENT_PASSWORD;

        if (CheckFunctions.checkPasswordFormat(password))
            return  LoginMessages.INVALID_PASSWORD_FORMAT;

        currentUser.setPassword(password);

        return LoginMessages.SUCCESSFUL;
    }
}
