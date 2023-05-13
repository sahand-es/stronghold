package view;

import model.Application;
import model.map.Block;
import view.enums.AllMenus;
import view.enums.commands.MapMenuCommands;
import view.enums.commands.SignUpCommands;
import view.enums.commands.TradeCommands;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeMenu {
    private static HashMap<String, String> tradeData;

    static {
        tradeData = new HashMap<>();
    }
    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        while(true){

            command = scanner.nextLine();

            if(TradeCommands.getMatcher(command, TradeCommands.BACK) != null){
                //todo go back
            }
            else if(TradeCommands.getMatcher(command, TradeCommands.TRADE) != null){
                extractTradeCommand(command);
                System.out.println(tradeData);
            }
//            else if(MapMenuCommands.getMatcher(command,MapMenuCommands.SHOW_DETAILS) != null){
//                checkShowMap(command);
//            }
//            else if(MapMenuCommands.getMatcher(command,MapMenuCommands.SHOW_MENU) != null){
//                System.out.println("You're in Map Menu!");
//            }
            else
                System.out.println("My liege, that's an invalid command!");


//            switch (Application.getCurrentMenu()){
//                case MAIN_MENU:
//                    return;
//                default:
//                    break;
//            }
        }

    }

    public static HashMap<String, String> extractTradeCommand(String command) {
        if(tradeData != null){ tradeData.clear(); }
        String type = "";
        String amount = "";
        String price = "";
        String tradeMessage = "";

        String regex = TradeCommands.getRegexARGUMENT();
        Pattern pattern = Pattern.compile(regex);
        Matcher checkMatcher = pattern.matcher(command);

        while (checkMatcher.find()) {
            String argName = checkMatcher.group("argument");
            String argNameSpace = checkMatcher.group("argumentSpace");
            String argVal;
            String argValSpace;
            if (argName != null) {
                argVal = checkMatcher.group("firstString");
                if (argName.equals("t") && type.equals("")){
                    type = argVal;
                }
                else if (argName.equals("a") && amount.equals("")){
                    amount = argVal;
                }
                else if (argName.equals("p") && price.equals("")){
                    price = argVal;
                }
                else if (argName.equals("m") && tradeMessage.equals("")){
                    tradeMessage = argVal;
                }
                else {
                    System.out.println("My liege, that's an invalid argument in trade request command!");
                    if(tradeData != null){ tradeData.clear(); }
                    tradeData = null;
                    return null;
                }
            }
            else {
                argValSpace = checkMatcher.group("firstStringSpace");
                if (argNameSpace.equals("t") && type.equals("")){
                    type = argValSpace;
                }
                else if (argNameSpace.equals("a") && amount.equals("")){
                    amount = argValSpace;
                }
                else if (argNameSpace.equals("p") && price.equals("")){
                    price = argValSpace;
                }
                else if (argNameSpace.equals("m") && tradeMessage.equals("")){
                    tradeMessage = argValSpace;
                }
                else {
                    System.out.println("My liege, that's an invalid argument in trade request command!");
                    if(tradeData != null){ tradeData.clear(); }
                    tradeData = null;
                    return null;
                }
            }

            command = command.replaceAll(checkMatcher.group().toString().trim(),"");


        }

        if (TradeCommands.getMatcher(command, TradeCommands.FINAL_TRADE_CHECK) != null){
            tradeData.put("type", type);
            tradeData.put("amount", amount);
            tradeData.put("price", price);
            tradeData.put("tradeMessage", tradeMessage);

            return tradeData;
        }
        else{
            System.out.println("My liege, that's an invalid argument in trade request command!");
            tradeData.clear();
            return null;
        }
    }


    public static void main(String[] args) {
        TradeMenu tradeMenu = new TradeMenu();
        tradeMenu.run();
    }
}
