package view;

import model.Application;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.MainMenuCommands;
import view.enums.commands.ProfileCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainMenu
{
    private static int x;
    private static int y;

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        MainMenu.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        MainMenu.y = y;
    }

    public static void run() throws InterruptedException {
        System.out.println("You're now in Main Menu!");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        Application.setCurrentMenu(AllMenus.MAIN_MENU);

        while(true){

            if(Application.getCurrentUser() == null){
                Application.setCurrentMenu(AllMenus.LOGIN_MENU);
                System.out.println("There is no logged in user!");
            }

            else{
                command = scanner.nextLine();
                Matcher matcher;
                if(MainMenuCommands.getMatcher(command,MainMenuCommands.EXIT) != null){
                    System.exit(0);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.LOGOUT) != null){
                    System.out.println("user logged out successfully!");
                    Application.setCurrentUser(null);
                    DataManager.saveLoggedIn(null);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.PROFILE_MENU) != null){
                    Application.setCurrentMenu(AllMenus.PROFILE_MENU);
                }
                else if((matcher = (MainMenuCommands.getMatcher(command,MainMenuCommands.START_GAME))) != null){
                    //todo start game
                    Application.setCurrentMenu(AllMenus.GAME_MENU);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MAP) != null){
                   checkShowMAp(command);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MENU) != null){
                    System.out.println("You're in Main Menu!");
                }
                else
                    System.out.println("My liege, that's an invalid command!");

            }


            switch (Application.getCurrentMenu()){
                case LOGIN_MENU:
                    System.out.println("You're now in Login Menu!");
                    LoginMenu.run();
                    break;
                case PROFILE_MENU:
                    System.out.println("You're now in Profile Menu!");
                    ProfileMenu.run();
                    break;
                case GAME_MENU:
                    System.out.println("You're now in Game Menu!");
                    GameMenu.run();
                    break;
                case MAP_MENU:
                    System.out.println("You're now in Map Menu!");
                    MapMenu.run(getX(),getY());
                    break;
                default:
                    break;
            }
        }

    }

    private static void checkShowMAp(String command){
        String x,y;

        String regexForX = MainMenuCommands.getRegexForX();
        Matcher matcherX = Pattern.compile(regexForX).matcher(command);
        if(!matcherX.find()){
            System.out.println("You must give x!");
            return;
        }
        else
            x = matcherX.group("xNum");

        command = command.replaceAll(matcherX.group().toString().trim(),"");

        String regexForY = MainMenuCommands.getRegexForY();
        Matcher matcherY = Pattern.compile(regexForY).matcher(command);
        if(!matcherY.find()){
            System.out.println("You must give y!");
            return;
        }
        else
            y = matcherY.group("yNum");

        command = command.replaceAll(matcherY.group().toString().trim(),"");

        if (MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MAP_FINAL_CHECK) == null){
            System.out.println("Invalid argument in show map command!");
            return;
        }

        setX(Integer.parseInt(x));
        setY(Integer.parseInt(y));
        Application.setCurrentMenu(AllMenus.MAP_MENU);

    }

}
