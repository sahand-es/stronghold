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
        Matcher matcher;

        while(true){

            command = scanner.nextLine();

            if(TradeCommands.getMatcher(command, TradeCommands.BACK) != null){
                System.out.println("You're now in Game Menu!");
                Application.setCurrentMenu(AllMenus.GAME_MENU);
                return;
            }
            else if(TradeCommands.getMatcher(command, TradeCommands.SHOW_MENU) != null){
                System.out.println("You're in Trade Menu!");
            }
            else if((matcher = TradeCommands.getMatcher(command, TradeCommands.TRADE)) != null){
                checkTradeRequest(matcher);
            } else if ((matcher = TradeCommands.getMatcher(command, TradeCommands.TRADE_ACCEPT)) != null){
                checkTradeAccept(matcher);
            } else if (TradeCommands.getMatcher(command, TradeCommands.TRADE_LIST) != null){
                TradeControl.showAllTrades();
            } else if (TradeCommands.getMatcher(command, TradeCommands.TRADE_LIST) != null){
                TradeControl.showTradeHistory();
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


    private static void checkTradeAccept(Matcher matcher){
        int id = Integer.parseInt(matcher.group("id"));
        String message1 = matcher.group("message").trim();

        TradeMessages message = TradeControl.acceptTrade(id,message1);

        switch (message){
            case TRADE_ID_DOES_NOT_EXIST:
                System.out.println("no trade with this id exist");
                break;

            case NOT_ENOUGH_RESOURCES:
                System.out.println("you dont have enough resources");
                break;

            case SUCCESS:
                System.out.println("trade done successfully");
        }
    }
    private static void checkTradeRequest(Matcher matcher){
        String resourceType = matcher.group("resourceType");
        int amount = Integer.parseInt(matcher.group("amount"));
        int price = Integer.parseInt(matcher.group("price"));
        String message1 = matcher.group("message").trim();



        TradeMessages message = TradeControl.makeTrade(resourceType,amount,price,message1);

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

