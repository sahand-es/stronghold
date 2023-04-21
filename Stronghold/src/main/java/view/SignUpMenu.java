package view;

import controller.SignUpControl;
import view.enums.commands.SignUpCommands;
import view.enums.messages.SignUpMessages;

import java.util.regex.Matcher;

public class SignUpMenu
{
    public static void run()
    {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while(true){
            command = scanner.nextLine();
            if ((matcher = SignUpCommands.getMatcher(command, SignUpCommands.EXIT )) != null){
                return;
            }
            else if((matcher = SignUpCommands.getMatcher(command, SignUpCommands.CREATE )) != null){
                checkCreateUser(matcher);
            }
            else
                System.out.println("My liege, that's an invalid command!");
        }
    }


    private static void checkCreateUser(Matcher matcher){
        String username = matcher.group("username");
        if (username != null){username = username.trim();}
        String password = matcher.group("password");
        if (password != null){password = password.trim();}
        String passwordConfirm = matcher.group("confirmPassword");
        if (passwordConfirm != null){passwordConfirm = passwordConfirm.trim();}
        String nickname = matcher.group("nickname");
        if (nickname != null){nickname = nickname.trim();}
        String email = matcher.group("email");
        if (email != null){email = email.trim();}
        String slogan = matcher.group("slogan");
        SignUpMessages message = SignUpControl.Signup(username,password,passwordConfirm,nickname,email,slogan);
        switch (message) {
            case EMPTYUSERNAME:
                System.out.println("My liege, you must give a username!");
                break;
            case EMPTYPASSWORD:
                System.out.println("My liege, you must give a password!");
                break;
            case EMPTYPASSWORDCONFIRM:
                System.out.println("My liege, you must confirm your password");
                break;
            case INVALIDPASSCONFIRM:
                System.out.println("My liege, your password doesn't match with the password confirmation");
                break;
            case EMPTYNICKNAME:
                System.out.println("My liege, you must give a nickname!");
                break;
            case EMPTYEMAIL:
                System.out.println("My liege, you must give an email!");
                break;
            case INVALIDUSERFORMAT:
                System.out.println("My liege, your username format is invalid");
                break;
            case USEREXIST:
                System.out.println("My liege, this username already exist");
                break;
            case INSUFFICIENTPASS:
                System.out.println("My liege, your password must be at least 6 characters");
                break;
            case INVALIDPASSFORMAT:
                System.out.println("My liege, your password format is invalid");
                break;
            case EXISTINGEMAIL:
                System.out.println("My liege, this email already exist");
                break;
            case INVALIDEMAIL:
                System.out.println("My liege, your email format is invalid");
                break;
            case INVALIDCOMMANDCOMBINATION:
                System.out.println("My liege, you have gave an invalid combination");
                break;
            case SUCCESS:
                System.out.println("Your account has been successfully created!");
                break;
            case FAILED:
                System.out.println("SignUp Failed!");
                break;
            default:
                break;
        }
    }

    public static Boolean secondLayerCheckRandom(String password){
        System.out.println("Your random password is: " + password);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String passCheck;
        while (true){
            System.out.print("Please re-enter your password here:");
            passCheck = scanner.nextLine();
            if (passCheck.equals(password)){
                return true;
            }
            else if (passCheck.equals("back")) {
                return false;
            }
            else
                System.out.println("Password doesn't match! in order to go to signup menu type \"back\"");
        }
    }

    public static void main(String[] args){
        run();
    }

}
