package view;

import model.Application;
import model.Game;
import model.map.Block;
import model.map.Map;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.GameCommands;
import view.enums.commands.MainMenuCommands;
import view.enums.commands.MapMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu {

    public static void run() {
        Application.setCurrentMenu(AllMenus.GAME_MENU);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        while (true){
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
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_POPULARITY_FACTORS) != null){
                //todo show popularity factors
            }
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_POPULARITY) != null){
                //todo show popularity
            }
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_FOOD) != null){
                //todo show food list
            }
            else if(GameCommands.getMatcher(command,GameCommands.FOOD_RATE) != null){
                //todo get food rate
            }
            else if(GameCommands.getMatcher(command,GameCommands.FOOD_RATE_SHOW) != null){
                //todo show food rate
            }
            else if(GameCommands.getMatcher(command,GameCommands.TAX_RATE) != null){
                //todo get tax rate
            }
            else if(GameCommands.getMatcher(command,GameCommands.TAX_RATE_SHOW) != null){
                //todo show tax rate
            }
            else if(GameCommands.getMatcher(command,GameCommands.FEAR_RATE) != null){
                //todo get tax rate
            }
            else if(GameCommands.getMatcher(command,GameCommands.FEAR_RATE_SHOW) != null){
                //todo show tax rate
            }
            else if(GameCommands.getMatcher(command,GameCommands.CREATE_BUILDING) != null){
                checkCreateBuilding(command);
            }
            else if(GameCommands.getMatcher(command,GameCommands.SELECT_BUILDING) != null){
                checkSelectBuilding(command);
            }
            else if(GameCommands.getMatcher(command,GameCommands.CREATE_UNIT) != null){
                checkCreateUnit(command);
            }
            else if(GameCommands.getMatcher(command,GameCommands.REPAIR) != null){
                //todo repair
            }
            //todo get select unit

            //todo get move unit

            //todo get patrol unit

            //todo get set state

            //todo get attack -e

            //todo get attack

            //todo get pour oil

            //todo get dig tunnel

            //todo get build equipment

            //todo get disband unit
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

    private static void checkCreateBuilding(String command){
        String x,y,type;

        String regexForX = GameCommands.getRegexForX();
        Matcher matcherX = Pattern.compile(regexForX).matcher(command);
        if(!matcherX.find()){
            System.out.println("You must give x!");
            return;
        }
        else
            x = matcherX.group("xNum");

        command = command.replaceAll(matcherX.group().toString().trim(),"");

        String regexForY = GameCommands.getRegexForY();
        Matcher matcherY = Pattern.compile(regexForY).matcher(command);
        if(!matcherY.find()){
            System.out.println("You must give y!");
            return;
        }
        else
            y = matcherY.group("yNum");

        command = command.replaceAll(matcherY.group().toString().trim(),"");

        String regexForType = GameCommands.getRegexForType();
        Matcher matcherType = Pattern.compile(regexForType).matcher(command);
        if(!matcherType.find()){
            System.out.println("You must give type!");
            return;
        }
        else
            type = matcherType.group("type");

        command = command.replaceAll(matcherType.group().toString().trim(),"");

        if (GameCommands.getMatcher(command, GameCommands.CREATE_BUILDING_CHECK) == null){
            System.out.println("Invalid argument in create building command!");
            return;
        }


        //todo give this to it's controller

    }

    private static void checkSelectBuilding(String command){
        String x,y;

        String regexForX = GameCommands.getRegexForX();
        Matcher matcherX = Pattern.compile(regexForX).matcher(command);
        if(!matcherX.find()){
            System.out.println("You must give x!");
            return;
        }
        else
            x = matcherX.group("xNum");

        command = command.replaceAll(matcherX.group().toString().trim(),"");

        String regexForY = GameCommands.getRegexForY();
        Matcher matcherY = Pattern.compile(regexForY).matcher(command);
        if(!matcherY.find()){
            System.out.println("You must give y!");
            return;
        }
        else
            y = matcherY.group("yNum");

        command = command.replaceAll(matcherY.group().toString().trim(),"");


        if (GameCommands.getMatcher(command, GameCommands.SELECT_BUILDING_CHECK) == null){
            System.out.println("Invalid argument in select building command!");
            return;
        }


        //todo give this to it's controller

    }

    private static void checkCreateUnit(String command){
        String type,count;

        String regexForType = GameCommands.getRegexForType();
        Matcher matcherType = Pattern.compile(regexForType).matcher(command);
        if(!matcherType.find()){
            System.out.println("You must give type!");
            return;
        }
        else
            type = matcherType.group("type");

        command = command.replaceAll(matcherType.group().toString().trim(),"");

        String regexForCount = GameCommands.getRegexForCount();
        Matcher matcherCount = Pattern.compile(regexForCount).matcher(command);
        if(!matcherCount.find()){
            System.out.println("You must give count!");
            return;
        }
        else
            count = matcherCount.group("count");

        command = command.replaceAll(matcherCount.group().toString().trim(),"");


        if (GameCommands.getMatcher(command, GameCommands.CREATE_UNIT_CHECK) == null){
            System.out.println("Invalid argument in create unit command!");
            return;
        }


        //todo give this to it's controller

    }


    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        gameMenu.run();
    }
}
