package view;

import model.Application;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.MainMenuCommands;

public class MapMenu {
    public static void run(){

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        while(true){


            command = scanner.nextLine();
            if(MainMenuCommands.getMatcher(command,MainMenuCommands.EXIT) != null){
                System.exit(0);
            }

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
