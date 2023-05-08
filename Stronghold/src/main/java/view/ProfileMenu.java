package view;

import model.Application;
import model.User;
import view.enums.AllMenus;
import view.enums.commands.ProfileCommands;

import java.util.regex.Matcher;

public class ProfileMenu
{
    private static User currentUser = Application.getCurrentUser();
    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while (true){
            command = scanner.nextLine();

            if (ProfileCommands.getMatcher(command,ProfileCommands.BACK) != null) {
                Application.setCurrentMenu(AllMenus.MAIN_MENU);
            }
            else if ( ProfileCommands.getMatcher(command,ProfileCommands.SHOW_MENU) != null) {
                System.out.println("You're in Profile Menu!");
            }
            else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_USERNAME)) != null) {
                //todo
            }
            else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_NICKNAME)) != null) {
                //todo
            }
            else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_PASSWORD)) != null) {
                //todo
            }
            else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_EMAIL)) != null) {
                //todo
            }
            else if ((matcher = ProfileCommands.getMatcher(command,ProfileCommands.CHANGE_SLOGAN)) != null) {
                //todo
            }
            else if (ProfileCommands.getMatcher(command,ProfileCommands.REMOVE_SLOGAN) != null) {
                //todo
            }
            else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_HIGH_SCORE) != null) {
                //todo
            }
            else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_RANK) != null) {
                //todo
            }
            else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_SLOGAN) != null) {
                //todo
            }
            else if (ProfileCommands.getMatcher(command,ProfileCommands.DISPLAY_ALL) != null) {
                //todo
            }
            else {
                System.out.println("My liege, that's an invalid command!");
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
        System.out.println("high score: " + currentUser);
    }
    private static void displayRank(){
        System.out.println("rank: " + currentUser);
    }
    private static void displaySlogan(){
        String slogan = currentUser.getSlogan();

        if (slogan == null)
            System.out.println("Slogan is empty!");
        else
            System.out.println(slogan);
    }

}
