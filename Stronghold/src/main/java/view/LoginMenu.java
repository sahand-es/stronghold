package view;

import controller.LoginControl;
import model.User;
import view.enums.commands.LoginCommands;
import view.enums.messages.LoginMessages;

import java.util.Scanner;
import java.util.regex.Matcher;
public class LoginMenu
{
    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while (true){
            command = scanner.nextLine();

            if (LoginCommands.getMatcher(command,LoginCommands.LOGOUT) != null){
                break;
            } else if ((matcher = LoginCommands.getMatcher(command,LoginCommands.LOGIN)) != null) {
                checkLogin(matcher);
            } else if ((matcher = LoginCommands.getMatcher(command,LoginCommands.FORGOT_PASSWORD)) != null) {
                forgotPassword(matcher ,scanner);
            } else if (LoginCommands.getMatcher(command,LoginCommands.REGISTER) != null) {
                //TODO: open signup menu.
            } else {
                System.out.println("invalid command!");
            }
        }

    }

    private static void checkLogin(Matcher matcher) {
        // TODO: handel time

        String username = matcher.group("username").trim();
        String password = matcher.group("password");
        // TODO: add stay logged in flag.

        LoginMessages message;
        message = LoginControl.checkLogin(username,password);

        switch (message){
            case EMPTY_USERNAME:
                System.out.println("You must enter a username!");
                break;

            case EMPTY_PASSWORD:
                System.out.println("You must give a password!");
                break;

            case USERNAME_DIDNT_MATCH:
                System.out.println("username doesn't exist!");
                break;

            case PASSWORD_DIDNT_MATCH:
                System.out.println("password doesn't match!");
                break;

            case SUCCESSFUL:
                System.out.println("user logged in successfully");
                break;

            default:
                break;
        }



    }

    private static void forgotPassword(Matcher matcher ,Scanner scanner) {
        String username = matcher.group("username").trim();

        LoginMessages message;
        message = LoginControl.checkForgotPassword(username);

        switch (message){
            case EMPTY_USERNAME:
                System.out.println("username is empty, please try again.");
                break;

            case USERNAME_DIDNT_MATCH:
                System.out.println("username didn't match, please try again.");
                break;

            case SUCCESSFUL:
                askSecurityQuestion(scanner);
                break;

            default:
                break;
        }

        LoginControl.clearCurrentUser();

    }


    private static void askSecurityQuestion(Scanner scanner){
        String input;
        String securityQuestion = LoginControl.getSecurityQuestion();
        boolean flag = true;

        while (flag) {
            System.out.println(securityQuestion);
            input = scanner.nextLine();

            LoginMessages message;
            message = LoginControl.checkAskSecurityQuestion(input);

            switch (message){
                case BACK:
                    flag = false;
                    break;

                case INCORRECT_ANSWER:
                    System.out.println("your answer is wrong!, please try again.");
                    break;

                case SUCCESSFUL:
                    getPassword(scanner);
                    flag = false;
                    break;

                default:
                    break;
            }
        }
    }

    private static void getPassword(Scanner scanner){
        String input;
        boolean flag = true;

        while (flag){
            System.out.println("please enter your password:");
            input = scanner.nextLine().trim();

            LoginMessages message;
            message = LoginControl.checkGetPassword(input);

            switch (message){
                case BACK:
                    flag = false;
                    break;

                case RANDOM:
                    //TODO: add random password.
                    flag = false;
                    break;

                case EMPTY_PASSWORD:
                    System.out.println("you must enter a password");
                    break;

                case INSUFFICIENT_PASSWORD:
                    System.out.println("your password must be at least 6 characters");
                    break;

                case INVALID_PASSWORD_FORMAT:
                    System.out.println("Invalid password format!, pleas chose another password");
                    break;

                case SUCCESSFUL:
                    System.out.println("your password changed successfully!");
                    flag = false;
                    break;

                default:
                    break;
            }


        }

    }



}
