package view;

import model.Application;
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

            //ToDo show Menu feature
            if (ProfileCommands.getMatcher(command,ProfileCommands.BACK) != null) {
                //ToDo switch to main menu
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
        Application.getCurrentUser().setSlogan(null);
    }

    private static void displayAll(){
        displayHighScore();
        displayRank();
        displaySlogan();
    }

    private static void displayHighScore(){
        System.out.println("high score: " + Application.getCurrentUser().getHighScore());
    }
    private static void displayRank(){
        System.out.println("rank: " + Application.getCurrentUser().getRank());
    }
    private static void displaySlogan(){
        String slogan = Application.getCurrentUser().getSlogan();

        if (slogan == null)
            System.out.println("Slogan is empty!");
        else
            System.out.println(slogan);
    }

}
