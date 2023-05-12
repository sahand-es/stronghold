package controller;

import model.Application;
import model.User;
import utility.CheckFunctions;
import view.Captcha;
import view.ProfileMenu;
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
        return ProfileMessages.SUCCESS;
    }

    public static ProfileMessages changeNickname(String nickname){
        if (CheckFunctions.checkNicknameFormat(nickname)){
            return ProfileMessages.INVALID_NICK_FORMAT;
        }

        User currentUser = Application.getCurrentUser();
        User userInArray = Application.getUserByUsername(currentUser.getUsername());
        currentUser.setNickname(nickname);
        userInArray.setNickname(nickname);
        return ProfileMessages.SUCCESS;
    }

    public static ProfileMessages changePassword(String oldPassword, String newPassword){
        User currentUser = Application.getCurrentUser();
        if (!currentUser.checkPassword(oldPassword)){
            return ProfileMessages.WRONG_PASSWORD;
        }
        if (newPassword.length() < 6){
            return ProfileMessages.INSUFFICIENT_PASS;
        }
        if (CheckFunctions.checkPasswordFormat(newPassword)){
            return ProfileMessages.INVALID_PASS_FORMAT;
        }
        if (oldPassword.equals(newPassword)){
            return ProfileMessages.SAME_PASS;
        }

        if (!Captcha.run()){
            return ProfileMessages.FAILED;
        }

        if (ProfileMenu.getPassConfirmation(newPassword)){
            return ProfileMessages.FAILED;
        }

        User userInArray = Application.getUserByUsername(currentUser.getUsername());
        currentUser.setPassword(newPassword);
        userInArray.setPassword(newPassword);

        return ProfileMessages.SUCCESS;
    }

    public static ProfileMessages changeEmail(String email){
        if (CheckFunctions.checkEmailFormat(email)){
            return ProfileMessages.INVALID_EMAIL;
        }
        if (CheckFunctions.checkEmailExits(email)){
            return ProfileMessages.EXISTING_EMAIL;
        }
        User currentUser = Application.getCurrentUser();
        User userInArray = Application.getUserByUsername(currentUser.getUsername());
        currentUser.setEmail(email);
        userInArray.setEmail(email);

        return ProfileMessages.SUCCESS;
    }
    public static ProfileMessages changeSlogan(String slogan){
        User currentUser = Application.getCurrentUser();
        User userInArray = Application.getUserByUsername(currentUser.getUsername());
        currentUser.setSlogan(slogan);
        userInArray.setSlogan(slogan);

        return ProfileMessages.SUCCESS;
    }

    public static ProfileMessages removeSlogan(){
        User currentUser = Application.getCurrentUser();
        User userInArray = Application.getUserByUsername(currentUser.getUsername());
        currentUser.setSlogan(null);
        userInArray.setSlogan(null);

        return ProfileMessages.SUCCESS;
    }
}
