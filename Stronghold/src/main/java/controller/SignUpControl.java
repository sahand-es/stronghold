package controller;

import utility.CheckFunctions;
import view.enums.messages.SignUpMessages;

public class SignUpControl
{
    public static SignUpMessages Signup(String username, String password,String passwordConfirm, String nickname, String email, String slogan) {
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
        if(password.equals("random") && passwordConfirm == null){
           //ToDo generate random password
        }
        if (slogan != null){
            if(slogan.equals("random")){
                //ToDo generate random slogan
            }
        }
        //ToDo new user(it'll be added to users arraylist)

        return SignUpMessages.SUCCESS;
    }

}
