package controller;

import model.User;
import utility.CheckFunctions;
import utility.RandomGenerators;
import view.SignUpMenu;
import view.enums.messages.SignUpMessages;

public class SignUpControl
{
    public static SignUpMessages Signup(String username, String password,String passwordConfirm, String nickname, String email, String slogan) {
        boolean randomCheck = false;
        if (username == null){
            return SignUpMessages.EMPTYUSERNAME;
        }
        if (password == null && passwordConfirm == null){
            return SignUpMessages.EMPTYPASSWORD;
        }
        if (!password.equals("random") && passwordConfirm == null){
            return SignUpMessages.EMPTYPASSWORDCONFIRM;
        }
        if (nickname == null){
            return SignUpMessages.EMPTYNICKNAME;
        }
        if (email == null){
            return SignUpMessages.EMPTYEMAIL;
        }
        if (!password.equals("random") && !password.equals(passwordConfirm)){
            return SignUpMessages.INVALIDPASSCONFIRM;
        }
        if (CheckFunctions.checkUsernameFormat(username)){
            return SignUpMessages.INVALIDUSERFORMAT;
        }
        if (CheckFunctions.checkUsernameExits(username)){
            return SignUpMessages.USEREXIST;
        }
        if (!password.equals("random") && password.length() < 6){
            return SignUpMessages.INSUFFICIENTPASS;
        }
        if (!password.equals("random") && CheckFunctions.checkPasswordFormat(password)){
            return SignUpMessages.INVALIDPASSFORMAT;
        }
        //ToDo check email format
        //ToDo check email existence
        if(password.equals("random") && passwordConfirm != null){
            return SignUpMessages.INVALIDCOMMANDCOMBINATION;
        }
        if (slogan != null){
            if(slogan.equals("random")){
                slogan = RandomGenerators.randomSlogan();
            }
        }
        if(password.equals("random") && passwordConfirm == null){
           password = RandomGenerators.randomPassword();
           if(SignUpMenu.secondLayerCheckRandom(password)){
               return SignUpMessages.SUCCESS;
           }
           else
               return SignUpMessages.FAILED;
        }
        return SignUpMessages.SUCCESS;
    }

}
