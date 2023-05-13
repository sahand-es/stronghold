package controller;

import model.Application;
import model.User;
import utility.*;
//import utility.DataManager;
//import utility.SecurityQuestions;
import view.Captcha;
import view.LoginMenu;
import view.enums.commands.*;
import view.enums.messages.*;
import view.enums.messages.SignUpMessages;

public class LoginControl {

    private static User currentUser;
    public static LoginMessages checkLogin(String username, String password, Boolean stay){

        User user = Application.getUserByUsername(username);
        if (user == null)
            return LoginMessages.USER_NOT_FOUND;

        if (!user.checkPassword(password))
            return LoginMessages.PASSWORD_DIDNT_MATCH;

        LoginMenu.setWrongAttempts(0);
        if (!Captcha.run()){
            return LoginMessages.FAILED;
        }

        Application.setCurrentUser(user);
        if(stay){
            DataManager.saveLoggedIn(Application.getCurrentUser());
        }
        return LoginMessages.SUCCESSFUL;
    }

    public static LoginMessages checkForgotPassword(String username){

        User user = Application.getUserByUsername(username);

        if (user == null)
            return LoginMessages.USER_NOT_FOUND;

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
        if (LoginCommands.getMatcher(input,LoginCommands.BACK) != null)
            return LoginMessages.BACK;

        if (!currentUser.checkAnswer(input))
            return LoginMessages.INCORRECT_ANSWER;


        return LoginMessages.SUCCESSFUL;
    }

    public static LoginMessages checkGetPassword(String password){
        if (LoginCommands.getMatcher(password,LoginCommands.BACK) != null)
            return LoginMessages.BACK;

        if (LoginCommands.getMatcher(password,LoginCommands.RANDOM) != null)
            return LoginMessages.RANDOM;

        if (password.equals(""))
            return LoginMessages.EMPTY_PASSWORD;

        if (password.length() < 6)
            return LoginMessages.INSUFFICIENT_PASSWORD;

        if (CheckFunctions.checkPasswordFormat(password))
            return  LoginMessages.INVALID_PASSWORD_FORMAT;

        if (!Captcha.run()){
            return LoginMessages.FAILED;
        }

        currentUser.setPassword(password);

        return LoginMessages.SUCCESSFUL;
    }

    public static void setPassword(String password){
        currentUser.setPassword(password);
    }
}
