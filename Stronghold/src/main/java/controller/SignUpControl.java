package controller;

import model.Application;
import model.User;
import utility.DataManager;
import view.Captcha;
import utility.CheckFunctions;
import utility.RandomGenerators;
import view.SignUpMenu;
import view.enums.messages.SignUpMessages;

import java.util.HashMap;

public class SignUpControl {

    public static SignUpMessages signUp(HashMap<String,String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String passwordConfirm = data.get("passwordConfirm");
        String nickname = data.get("nickname");
        String email = data.get("email");
        String slogan = data.get("slogan");
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
        if (CheckFunctions.checkEmailFormat(email)){
           return SignUpMessages.INVALID_EMAIL;
        }
        if (CheckFunctions.checkEmailExits(email)){
            return SignUpMessages.EXISTING_EMAIL;
        }
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

        HashMap<String, String> QA = SignUpMenu.getSecurityQuestionAnswer();

        if (QA == null) {
            return SignUpMessages.FAILED;
        }

        int questionNumber = Integer.parseInt(QA.get("questionNumber"));
        String answer = QA.get("answer");

        if (!Captcha.run()){
            return SignUpMessages.FAILED;
        }

        User user = new User(username,password,nickname,email,questionNumber - 1,answer);
        DataManager.saveUsers();

        return SignUpMessages.SUCCESS;
    }

}
