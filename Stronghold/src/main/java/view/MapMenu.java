package view;

import model.Application;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.MainMenuCommands;
import view.enums.commands.MapMenuCommands;

public class MapMenu {
    public static void run(int x , int y){

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        while(true){

            //todo literally show the fucking map!

            command = scanner.nextLine();

            if(MapMenuCommands.getMatcher(command, MapMenuCommands.EXIT) != null){
                System.out.println("You're in Main Menu!");
                Application.setCurrentMenu(AllMenus.MAIN_MENU);
                return;
            }
            //todo get direction
            //todo show detail
            else if(MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MENU) != null){
                System.out.println("You're in Map Menu!");
            }
            else
                System.out.println("My liege, that's an invalid command!");



            switch (Application.getCurrentMenu()){
                case MAIN_MENU:
                    return;
                default:
                    break;
            }
        }

    }

}
