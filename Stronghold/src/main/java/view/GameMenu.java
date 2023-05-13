package view;

import model.Application;
import model.Game;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.GameCommands;
import view.enums.commands.MainMenuCommands;

public class GameMenu
{
    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        command = scanner.nextLine();
        if(GameCommands.getMatcher(command,GameCommands.BACK) != null){
            System.out.println("You're now in Main Menu!");
            Application.setCurrentMenu(AllMenus.MAIN_MENU);
            return;
        }
        else if(GameCommands.getMatcher(command,GameCommands.SHOW_MENU) != null){
            System.out.println("You're now in Game Menu!");
        }
        else if(GameCommands.getMatcher(command,GameCommands.TRADE) != null){
            Application.setCurrentMenu(AllMenus.TRADE_MENU);
        }
        else
            System.out.println("My liege, that's an invalid command!");


        switch (Application.getCurrentMenu()){
        case TRADE_MENU:
            System.out.println("You're now in Trade Menu!");
            TradeMenu.run();
            break;
        default:
            break;
        }
    }
}
