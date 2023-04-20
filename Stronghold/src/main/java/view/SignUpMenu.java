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
        String password = matcher.group("password");
        String passwordConfirm = matcher.group("confirmPassword");
        String nickname = matcher.group("nickname");
        String email = matcher.group("email");
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
            case EMPTYNICKNAME:
                System.out.println("My liege, you must give a nickname!");
                break;
            case EMPTYEMAIL:
                System.out.println("My liege, you must give an email!");
                break;
            case SUCCESS:
                System.out.println("Your account has been successfully created!");
                break;
            default:
                break;
        }
    }

    public static void main(String[] args){
        run();
    }

}
