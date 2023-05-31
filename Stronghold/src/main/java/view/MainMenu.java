package view;

import model.Database;
import model.Game;
import model.map.Map;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.MainMenuCommands;

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
        System.out.println("You're now in view.Main Menu!");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        Database.setCurrentMenu(AllMenus.MAIN_MENU);

        while(true){

            if(Database.getCurrentUser() == null){
                Database.setCurrentMenu(AllMenus.LOGIN_MENU);
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
                    Database.setCurrentUser(null);
                    DataManager.saveLoggedIn(null);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.PROFILE_MENU) != null){
                    Database.setCurrentMenu(AllMenus.PROFILE_MENU);
                }
                else if((matcher = (MainMenuCommands.getMatcher(command,MainMenuCommands.START_GAME))) != null){

                    int numberOfGovernments = Integer.parseInt(matcher.group("number"));
                    int height = Integer.parseInt(matcher.group("height"));
                    int width = Integer.parseInt(matcher.group("width"));

                    if (numberOfGovernments > 8){
                        System.out.println("number of players is mor than 8. Please try again");;
                    } else {
                        Map map = new Map(height,width);
                        Game game = new Game(map , numberOfGovernments);

                        Database.setCurrentMap(map);
                        Database.setCurrentGame(game);
                        Database.setCurrentMenu(AllMenus.GAME_MENU);
                    }
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MAP) != null){
                   checkShowMAp(command);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MENU) != null){
                    System.out.println("You're in view.Main Menu!");
                }
                else
                    System.out.println("My liege, that's an invalid command!");

            }


            switch (Database.getCurrentMenu()){
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
        Database.setCurrentMenu(AllMenus.MAP_MENU);

    }

}
