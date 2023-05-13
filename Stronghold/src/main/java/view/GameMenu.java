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
            else if(GameCommands.getMatcher(command,GameCommands.SELECT_UNIT) != null){
                checkSelectUnit(command);
            }
            else if(GameCommands.getMatcher(command,GameCommands.MOVE_UNIT) != null){
                checkMoveUnit(command);
            }
            else if(GameCommands.getMatcher(command,GameCommands.PATROL_UNIT) != null){
                checkPatrolUnit(command);
            }

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

    private static void checkSelectUnit(String command){
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


        if (GameCommands.getMatcher(command, GameCommands.SELECT_UNIT_CHECK) == null){
            System.out.println("Invalid argument in select unit command!");
            return;
        }


        //todo give this to it's controller

    }


    private static void checkMoveUnit(String command){
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


        if (GameCommands.getMatcher(command, GameCommands.MOVE_UNIT_CHECK) == null){
            System.out.println("Invalid argument in move unit command!");
            return;
        }


        //todo give this to it's controller

    }


    private static void checkPatrolUnit(String command){
        String x1,y1;
        String x2,y2;

        String regexForX1 = GameCommands.getRegexForX1();
        Matcher matcherX1 = Pattern.compile(regexForX1).matcher(command);
        if(!matcherX1.find()){
            System.out.println("You must give x1!");
            return;
        }
        else
            x1 = matcherX1.group("x1Num");

        command = command.replaceAll(matcherX1.group().toString().trim(),"");

        String regexForY1 = GameCommands.getRegexForY1();
        Matcher matcherY1 = Pattern.compile(regexForY1).matcher(command);
        if(!matcherY1.find()){
            System.out.println("You must give y1!");
            return;
        }
        else
            y1 = matcherY1.group("y1Num");

        command = command.replaceAll(matcherY1.group().toString().trim(),"");


        String regexForX2 = GameCommands.getRegexForX2();
        Matcher matcherX2 = Pattern.compile(regexForX2).matcher(command);
        if(!matcherX2.find()){
            System.out.println("You must give x2!");
            return;
        }
        else
            x2 = matcherX2.group("x2Num");

        command = command.replaceAll(matcherX2.group().toString().trim(),"");

        String regexForY2 = GameCommands.getRegexForY2();
        Matcher matcherY2 = Pattern.compile(regexForY2).matcher(command);
        if(!matcherY2.find()){
            System.out.println("You must give y2!");
            return;
        }
        else
            y2 = matcherY2.group("y2Num");

        command = command.replaceAll(matcherY2.group().toString().trim(),"");

        if (GameCommands.getMatcher(command, GameCommands.PATROL_UNIT_CHECK) == null){
            System.out.println("Invalid argument in patrol unit command!");
            return;
        }


        //todo give this to it's controller

    }


    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        gameMenu.run();
    }
}
