package view;

import controller.LoginControl;

import model.Database;
import utility.CheckFunctions;
import utility.RandomGenerators;
import view.enums.AllMenus;
import view.enums.commands.LoginCommands;
import view.enums.messages.LoginMessages;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu
{
    private static int wrongAttempts ;
    public static void run() throws InterruptedException {

        setWrongAttempts(0);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while (true){
            command = scanner.nextLine();

            if (LoginCommands.getMatcher(command,LoginCommands.EXIT) != null){
                System.exit(0);
            }
            else if ( LoginCommands.getMatcher(command, LoginCommands.SHOW_MENU) != null) {
                System.out.println("You're in Login Menu!");
            }
            else if (LoginCommands.getMatcher(command,LoginCommands.LOGIN) != null) {
                checkLogin(command);
            } else if ((matcher = LoginCommands.getMatcher(command,LoginCommands.FORGOT_PASSWORD)) != null) {
                forgotPassword(matcher ,scanner);
            } else if (LoginCommands.getMatcher(command,LoginCommands.SIGNUP) != null) {
                Database.setCurrentMenu(AllMenus.SIGNUP_MENU);
            } else {
                System.out.println("My liege, that's an invalid command!");
            }

            switch (Database.getCurrentMenu()){
                case SIGNUP_MENU:
                    System.out.println("You're now in SignUp Menu!");
                    SignUpMenu.run();
                    break;
                case MAIN_MENU:
                    System.out.println("You're now in view.Main Menu!");
                    return;
                default:
                    break;
            }
        }

    }

    private static void checkLogin(String command) throws InterruptedException {
        String username = "";
        String password = "";
        boolean stayLogged = false;

        String regexUSER = LoginCommands.getRegexUSER();
        Matcher userMatcher = Pattern.compile(regexUSER).matcher(command);

        if (userMatcher.find()){
            username = CheckFunctions.getUserFromMatcher(userMatcher);
            command = command.replaceAll(userMatcher.group().toString().trim(),"");

            if (username == null){
                System.out.println("My liege, you must give username to log in!");
                return;
            }
        }

        String regexPASS = LoginCommands.getRegexPASS();
        Matcher passMatcher = Pattern.compile(regexPASS).matcher(command);

        if (passMatcher.find()){
            password = CheckFunctions.getPassFromMatcher(passMatcher);
            command = command.replaceAll(passMatcher.group().toString().trim(),"");

            if (password == null){
                System.out.println("My liege, you must give password to log in!");
                return;
            }
        }

        String regexStayLoggedIn = LoginCommands.getRegexStayLoggedIn();
        Matcher stayLoggedInMatcher = Pattern.compile(regexStayLoggedIn).matcher(command);

        if (stayLoggedInMatcher.find()){
            stayLogged = true;
            command = command.replaceAll(stayLoggedInMatcher.group().toString().trim(),"");
        }


        if (LoginCommands.getMatcher(command,LoginCommands.FINAL_LOGIN_CHECK) == null){
            System.out.println("My liege, there is an invalid argument in login command");
            return;
        }

        LoginMessages messages = LoginControl.checkLogin(username,password,stayLogged);

        switch (messages){
            case USER_NOT_FOUND:
                System.out.println("There is no user with this username!");
                break;
            case PASSWORD_DIDNT_MATCH:
                setWrongAttempts(getWrongAttempts() + 1);
                System.out.println("Incorrect password, you had " + getWrongAttempts() + " wrong attempts!");
                for (int timeRemaining = getWrongAttempts() * 5; timeRemaining > 0; timeRemaining--) {
                    System.out.println(timeRemaining + "...");
                    Thread.sleep(1000);
                }
                break;
            case FAILED:
                System.out.println("Login Failed!");
                break;
            case SUCCESSFUL:
                System.out.println("Welcome back my liege!");
                Database.setCurrentMenu(AllMenus.MAIN_MENU);
                break;
            default:
                break;
        }

    }

    private static void forgotPassword(Matcher matcher ,Scanner scanner) {
        String username = CheckFunctions.getUserFromMatcher(matcher);

        if (username == null){
            System.out.println("My liege you must give a username!");
            return;
        }

        LoginMessages message;
        message = LoginControl.checkForgotPassword(username);

        switch (message){
            case USER_NOT_FOUND:
                System.out.println("This Username doesn't exist!");
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
                    System.out.println("Forgot pass command terminated!");
                    break;

                case INCORRECT_ANSWER:
                    System.out.println("Your answer is wrong!, please try again or type \"back\" to cancel");
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
            System.out.println("Please enter your new password:");
            input = scanner.nextLine().trim();

            LoginMessages message;
            message = LoginControl.checkGetPassword(input);

            switch (message){
                case BACK:
                    flag = false;
                    System.out.println("Forgot pass command terminated!");
                    break;

                case RANDOM:
                    getRandomPassword(scanner);
                    flag = false;
                    break;

                case EMPTY_PASSWORD:
                    System.out.println("You must enter a password or type \"back\" to cancel");
                    break;

                case INSUFFICIENT_PASSWORD:
                    System.out.println("Your password must be at least 6 characters");
                    break;

                case INVALID_PASSWORD_FORMAT:
                    System.out.println("Invalid password format!, pleas choose " +
                            "another password or type \"back\" to cancel");
                    break;

                case FAILED:
                    System.out.println("Password change was failed!");
                    flag = false;
                    break;

                case SUCCESSFUL:
                    System.out.println("Your password changed successfully!");
                    flag = false;
                    break;

                default:
                    break;
            }
        }

    }

    public static void getRandomPassword(Scanner scanner){
        String password;
        String input;

        while (true) {
            password = RandomGenerators.randomPassword();
            System.out.println("Your random password is: " + password);
            System.out.print("Please re-enter your random password :");
            input = scanner.nextLine().trim();

            if (LoginCommands.getMatcher(input,LoginCommands.BACK) != null){
                System.out.println("Password change was cancelled!");
                break;
            }
            else if (input.equals(password)){
                if (Captcha.run()){
                    LoginControl.setPassword(password);
                    System.out.println("Your password changed successfully!");
                }
                else
                    System.out.println("Password change was failed!");

                break;
            }
            else {
                System.out.println("Password doesn't match! in order to go to login menu type \"back\"");
            }
        }

    }


    public static int getWrongAttempts() {
        return wrongAttempts;
    }

    public static void setWrongAttempts(int wrongAttempts) {
        LoginMenu.wrongAttempts = wrongAttempts;
    }
}
