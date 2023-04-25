package view;

import view.enums.commands.ProfileCommands;

import java.util.regex.Matcher;

public class ProfileMenu
{
    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while (true){
            command = scanner.nextLine();

            if (ProfileCommands.getMatcher(command,ProfileCommands.BACK) != null) {
                break;
            } else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_USERNAME)) != null) {
                checkChangeUsername(matcher);
            } else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_NICKNAME)) != null) {
                checkChangeNickname(matcher);
            } else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_PASSWORD)) != null) {
                checkChangePassword(matcher);
            } else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_EMAIL)) != null) {
                checkChangeEmail(matcher);
            } else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_SLOGAN)) != null) {
                checkChangeSlogan(matcher);
            } else if (ProfileCommands.getMatcher(command,ProfileCommands.REMOVE_SLOGAN) != null) {
                checkRemoveSlogan();
            } else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_HIGH_SCORE) != null) {
                displayHighScore();
            } else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_RANK) != null) {
                displayRank();
            } else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_SLOGAN) != null) {
                displaySlogan();
            } else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_ALL) != null) {
                displayAll();
            } else {
                System.out.println("invalid command!");
            }
        }

    }

    private static void checkChangeUsername(Matcher matcher){

    }

    private static void checkChangeNickname(Matcher matcher){

    }

    private static void checkChangePassword(Matcher matcher){

    }

    private static void checkChangeEmail(Matcher matcher){

    }

    private static void checkChangeSlogan(Matcher matcher){

    }

    private static void checkRemoveSlogan(){

    }

    private static void displayAll(){

    }

    private static void displayHighScore(){

    }
    private static void displayRank(){

    }
    private static void displaySlogan(){

    }

}
