package view;

import controller.MarketControl;
import model.Database;
import view.enums.AllMenus;
import view.enums.commands.MarketCommands;

import java.util.regex.Matcher;

public class MarketMenu {

    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while (true) {

            command = scanner.nextLine();

            if ( MarketCommands.getMatcher(command, MarketCommands.BACK)!= null) {
                System.out.println("You're now in Game Menu!");
                Database.setCurrentMenu(AllMenus.GAME_MENU);
                return;
            } else if ((matcher = MarketCommands.getMatcher(command, MarketCommands.BUY)) != null) {
                checkBuy(matcher);
            } else if ((matcher = MarketCommands.getMatcher(command, MarketCommands.SELL)) != null){
                checkSell(matcher);
            } else if (MarketCommands.getMatcher(command, MarketCommands.SHOW_SHOP_LIST)!= null){
                System.out.println(MarketControl.showPrice());
            } else {
                System.out.println("Invalid command");
            }
        }
    }


    private static void checkBuy(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        String name = matcher.group("item").trim();

        if (MarketControl.checkBuy(name,amount)){
            System.out.println("Item bought successfully");
        } else {
            System.out.println("you cant buy this item");
        }
    }

    private static void checkSell(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        String name = matcher.group("item").trim();

        if (MarketControl.checkSell(name,amount)){
            System.out.println("Item soled successfully");
        } else {
            System.out.println("you cant sell this item");
        }
    }


}
