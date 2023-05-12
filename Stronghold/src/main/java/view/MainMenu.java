package view;

import model.Application;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.MainMenuCommands;


public class MainMenu
{
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
                if(MainMenuCommands.getMatcher(command,MainMenuCommands.EXIT) != null){
                    System.exit(0);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.LOGOUT) != null){
                    Application.setCurrentUser(null);
                    DataManager.saveLoggedIn();
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.PROFILE_MENU) != null){
                    Application.setCurrentMenu(AllMenus.PROFILE_MENU);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.GAME_MENU) != null){
                    Application.setCurrentMenu(AllMenus.GAME_MENU);
                }
                else if(MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MENU) != null){
                    System.out.println("You're in Main Menu!");
                }
                else
                    System.out.println("My liege, that's an invalid command!");

                //todo add map menu
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
                //todo add map menu run
                default:
                    break;
            }
        }

    }


}
