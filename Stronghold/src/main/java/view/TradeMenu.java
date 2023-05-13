package view;

import model.Application;
import model.map.Block;
import view.enums.AllMenus;
import view.enums.commands.MapMenuCommands;

public class TradeMenu {
    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        while(true){

            command = scanner.nextLine();

//            if(MapMenuCommands.getMatcher(command, MapMenuCommands.) != null){
//                System.out.println("You're in Main Menu!");
//                Application.setCurrentMenu(AllMenus.MAIN_MENU);
//                return;
//            }
//            else if(MapMenuCommands.getMatcher(command,MapMenuCommands.MAP) != null){
//                getCoordinates(command);
//            }
//            else if(MapMenuCommands.getMatcher(command,MapMenuCommands.SHOW_DETAILS) != null){
//                checkShowMap(command);
//            }
//            else if(MapMenuCommands.getMatcher(command,MapMenuCommands.SHOW_MENU) != null){
//                System.out.println("You're in Map Menu!");
//            }
//            else
//                System.out.println("My liege, that's an invalid command!");


//            switch (Application.getCurrentMenu()){
//                case MAIN_MENU:
//                    return;
//                default:
//                    break;
//            }
        }

    }
}
