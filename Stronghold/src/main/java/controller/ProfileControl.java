package controller;

import model.Application;
import model.User;
import utility.CheckFunctions;
import view.enums.messages.ProfileMessages;
import view.enums.messages.SignUpMessages;

public class ProfileControl
{

    public static ProfileMessages changeUsername(String username){
        if (CheckFunctions.checkUsernameFormat(username)){
            return ProfileMessages.INVALID_USER_FORMAT;
        }
        if (CheckFunctions.checkUsernameExits(username)){
            return ProfileMessages.USER_EXIST;
        }

        User currentUser = Application.getCurrentUser();
        User userInArray = Application.getUserByUsername(currentUser.getUsername());
        currentUser.setUsername(username);
        userInArray.setUsername(username);
        return ProfileMessages.SUCCES;
    }

    public static ProfileMessages changeNickname(String nickname){
        return null;
    }

    public static ProfileMessages changePassword(String password){
        return null;
    }

    public static ProfileMessages changeEmail(String email){
        return null;
    }
    public static ProfileMessages changeSlogan(String slogan){
        return null;
    }

    public static ProfileMessages removeSlogan(){
        return null;
    }
}
