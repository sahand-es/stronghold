package view;

import controller.TradeControl;
import model.Application;
import model.map.Block;
import view.enums.AllMenus;
import view.enums.commands.MapMenuCommands;
import view.enums.commands.SignUpCommands;
import view.enums.commands.TradeCommands;
import view.enums.messages.SignUpMessages;
import view.enums.messages.TradeMessages;

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
                System.out.println("You're in Game Menu!");
                Application.setCurrentMenu(AllMenus.GAME_MENU);
                return;
            }
            else if(TradeCommands.getMatcher(command, TradeCommands.TRADE) != null){
                extractTradeCommand(command);
                if (tradeData != null){
                    checkTradeRequest();
                }
            }

            else
                System.out.println("My liege, that's an invalid command!");
        }

    }

    public static HashMap<String, String> extractTradeCommand(String command) {
        if(tradeData != null){ tradeData.clear(); }
        else
            tradeData = new HashMap<>();
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
            tradeData = null;
            return null;
        }

    }


    private static void checkTradeRequest(){
        if(tradeData != null){
            TradeMessages message = TradeControl.makeTrade(tradeData);

            switch (message) {
                case TYPE_EMPTY:
                    System.out.println("My liege, you must give a type!");
                    break;
                case AMOUNT_EMPTY:
                    System.out.println("My liege, you must give a amount!");
                    break;
                case MESSAGE_EMPTY:
                    System.out.println("My liege, you must give a message!");
                    break;
                case PRICE_EMPTY:
                    System.out.println("My liege, you must give a price!");
                    break;
                case INVALID_AMOUNT:
                    System.out.println("My liege, that's an invalid format for amount!");
                    break;
                case INVALID_PRICE:
                    System.out.println("My liege, that's an invalid format for price!");
                    break;
                case INVALID_RESOURCE:
                    System.out.println("My liege, that's an invalid resource type!");
                    break;
                case NOT_ENOUGH_RESOURCES:
                    System.out.println("My liege, you don't have enough resources!");
                    break;

                case SUCCESS:
                    System.out.println("Trade request sent!");
                    Application.setCurrentMenu(AllMenus.LOGIN_MENU);
                    break;
                default:
                    break;
            }
        }
    }

    //todo delete psvm
//    public static void main(String[] args) {
//        TradeMenu tradeMenu = new TradeMenu();
//        tradeMenu.run();
//    }
}
