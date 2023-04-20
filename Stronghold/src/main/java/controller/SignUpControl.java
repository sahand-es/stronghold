package controller;

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
        if (passwordConfirm == null){
            return SignUpMessages.EMPTYPASSWORDCONFIRM;
        }
        if (nickname == null){
            return SignUpMessages.EMPTYNICKNAME;
        }
        if (email == null){
            return SignUpMessages.EMPTYEMAIL;
        }
        if (CheckFunctions.checkUsernameFormat(username)){
            return SignUpMessages.INVALIDUSERFORMAT;
        }
        if (CheckFunctions.checkUsernameExits(username)){
            return SignUpMessages.USEREXIST;
        }
        if (password.length() < 6){
            return SignUpMessages.INSUFFICIENTPASS;
        }
        if (CheckFunctions.checkPasswordFormat(password)){
            return SignUpMessages.INVALIDPASSFORMAT;
        }
        if (!password.equals(passwordConfirm)){
            return SignUpMessages.INVALIDPASSCONFIRM;
        }

        return SignUpMessages.SUCCESS;
    }

}
