package controller;

import utility.CheckFunctions;
import utility.RandomGenerators;
import view.SignUpMenu;
import view.enums.messages.SignUpMessages;

public class SignUpControl
{
    public static SignUpMessages Signup(String username, String password,String passwordConfirm, String nickname, String email, String slogan) {
        boolean randomCheck = false;
        if (username == null){
            return SignUpMessages.EMPTY_USERNAME;
        }
        if (password == null && passwordConfirm == null){
            return SignUpMessages.EMPTY_PASSWORD;
        }
        if (!password.equals("random") && passwordConfirm == null){
            return SignUpMessages.EMPTY_PASSWORD_CONFIRM;
        }
        if (nickname == null){
            return SignUpMessages.EMPTY_NICKNAME;
        }
        if (email == null){
            return SignUpMessages.EMPTY_EMAIL;
        }
        if (!password.equals("random") && !password.equals(passwordConfirm)){
            return SignUpMessages.INVALID_PASS_CONFIRM;
        }
        if (CheckFunctions.checkUsernameFormat(username)){
            return SignUpMessages.INVALID_USER_FORMAT;
        }
        if (CheckFunctions.checkUsernameExits(username)){
            return SignUpMessages.USER_EXIST;
        }
        if (!password.equals("random") && password.length() < 6){
            return SignUpMessages.INSUFFICIENT_PASS;
        }
        if (!password.equals("random") && CheckFunctions.checkPasswordFormat(password)){
            return SignUpMessages.INVALID_PASS_FORMAT;
        }
        //ToDo check email format
        //ToDo check email existence
        if(password.equals("random") && passwordConfirm != null){
            return SignUpMessages.INVALID_COMMAND_COMBINATION;
        }
        if (slogan != null){
            if(slogan.equals("random")){
                slogan = RandomGenerators.randomSlogan();
            }
        }
        if(password.equals("random") && passwordConfirm == null){
           password = RandomGenerators.randomPassword();
           if(!SignUpMenu.secondLayerCheckRandom(password)){
               return SignUpMessages.FAILED;
           }
        }
        String Answer = SignUpMenu.getSecurityQuestionAnswer();
        //Todo Get captcha confirmation
        //Todo add user
        return SignUpMessages.SUCCESS;
    }

}
