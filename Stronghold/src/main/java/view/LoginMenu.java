package view;

import view.enums.commands.LoginCommands;

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
            } else if (LoginCommands.getMatcher(command,LoginCommands.FORGOT_PASSWORD) != null) {
                checkForgotPassword();
            } else if (LoginCommands.getMatcher(command,LoginCommands.REGISTER) != null) {
                //TODO: open signup menu.
            } else {
                System.out.println("That's an invalid command, my Lord!");
            }
        }

    }

    private static void checkLogin(Matcher matcher) {
        // TODO: handel time

        String username = matcher.group("username").trim();
        String password = matcher.group("password");
        // TODO: add stay logged in flag.





    }

    private static void checkForgotPassword() {

    }


}
