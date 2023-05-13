package view;

import controller.GameControl;
import model.Application;
import view.enums.AllMenus;
import view.enums.commands.GameCommands;
import view.enums.messages.GameMessages;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu {



    public static void run() {
        Application.setCurrentMenu(AllMenus.GAME_MENU);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        while (true){
            command = scanner.nextLine();
            Matcher matcher;
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
                System.out.println("Food rate: " + GameControl.getCurrentGovernment().getFoodRate());
                System.out.println("Tax rate: " + GameControl.getCurrentGovernment().getTaxRate());
                System.out.println("Fear rate: " + GameControl.getCurrentGovernment().getFearRate());
            }
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_POPULARITY) != null){
                System.out.println("Popularity: " + GameControl.getCurrentGovernment().getPopularity());
            }
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_FOOD) != null){
                GameControl.getCurrentGovernment().getResource().printFoods();
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.FOOD_RATE)) != null){
                checkFoodRate(matcher);
            }
            else if(GameCommands.getMatcher(command,GameCommands.FOOD_RATE_SHOW) != null){
                System.out.println("Food rate: " + GameControl.getCurrentGovernment().getFoodRate());
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.TAX_RATE)) != null){
                checkTaxRate(matcher);
            }
            else if(GameCommands.getMatcher(command,GameCommands.TAX_RATE_SHOW) != null){
                System.out.println("Tax rate: " + GameControl.getCurrentGovernment().getTaxRate());
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.FEAR_RATE)) != null){
                checkTaxRate(matcher);
            }
            else if(GameCommands.getMatcher(command,GameCommands.FEAR_RATE_SHOW) != null){
                //todo (print this shit) show tax rate
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
                //todo repair this shit
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
            else if(GameCommands.getMatcher(command,GameCommands.SET) != null){
                checkSetState(command);
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.ATTACK)) != null){
                checkAttack(matcher);
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.POUR_OIL)) != null){
                checkPourOil(matcher);
            }
            else if(GameCommands.getMatcher(command,GameCommands.DIG) != null){
                checkDigTunnel(command);
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.BUILD_EQUIPMENT)) != null){
                checkBuildEquipment(matcher);
            }
            else if(GameCommands.getMatcher(command,GameCommands.DISBAND) != null){
                //todo disband this shit
            }
            else if(GameCommands.getMatcher(command,GameCommands.SET_TEXTURE_BLOCK) != null){
                checkSetTextureBlock(command);
            }
            else if(GameCommands.getMatcher(command,GameCommands.SET_TEXTURE_AREA) != null){
                checkSetTextureArea(command);
            }
            else if(GameCommands.getMatcher(command,GameCommands.CLEAR) != null){
                checkClear(command);
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.DROP_ROCK)) != null){
                checkDropRock(matcher);
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.DROP_TREE)) != null){
                checkDropTree(matcher);
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.DROP_BUILDING)) != null){
                checkDropBuilding(matcher);
            }
            else if((matcher = GameCommands.getMatcher(command,GameCommands.DROP_UNIT)) != null){
                checkDropUnit(matcher);
            }
            else if(GameCommands.getMatcher(command,GameCommands.NEXT_TURN) != null){
                //todo nextTurn this shit
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

    private static void checkFoodRate(Matcher matcher){
        String foodRateNumber = matcher.group("foodRateNumber");
        int rate = Integer.parseInt(foodRateNumber);
        GameMessages message = GameControl.checkFoodRate(rate);

        if (message.equals(GameMessages.INVALID_RATE)){
            System.out.println("rate is invalid");
        }
        if(message.equals(GameMessages.SUCCESS)){
            System.out.println("food rate:" + rate);
        }
    }

    private static void checkTaxRate(Matcher matcher){
        String TaxRateNumber = matcher.group("taxRateNumber");
        int rate = Integer.parseInt(TaxRateNumber);
        GameMessages message = GameControl.checkTaxRate(rate);

        if (message.equals(GameMessages.INVALID_RATE)){
            System.out.println("rate is invalid");
        }
        if(message.equals(GameMessages.SUCCESS)){
            System.out.println("tax rate:" + rate);
        }
    }

    private static void checkFearRate(Matcher matcher){
        //todo ina string e ha bepaaaaa!
        String foodRateNumber = matcher.group("fearRateNumber");


        //todo give this to it's controller

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


    private static void checkSetState(String command){
        String x,y,state;

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

        String regexState = GameCommands.getRegexState();
        Matcher matcherState = Pattern.compile(regexState).matcher(command);
        if(!matcherState.find()){
            System.out.println("You must give state!");
            return;
        }
        else
            state = matcherState.group("state");

        command = command.replaceAll(matcherState.group().toString().trim(),"");


        if (GameCommands.getMatcher(command, GameCommands.SET_CHECK) == null){
            System.out.println("Invalid argument in set state command!");
            return;
        }


        //todo give this to it's controller

    }

    private static void checkAttack(Matcher matcher){
        String x,y;

        x = matcher.group("xNumber");
        y = matcher.group("yNumber");


        //todo give this to it's controller

    }

    private static void checkPourOil(Matcher matcher){
        String direction = matcher.group("direction");


        //todo give this to it's controller

    }

    private static void checkDigTunnel(String command){
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


        if (GameCommands.getMatcher(command, GameCommands.DIG_CHECK) == null){
            System.out.println("Invalid argument in dig tunnel command!");
            return;
        }


        //todo give this to it's controller

    }

    private static void checkBuildEquipment(Matcher matcher){
        String equipment = matcher.group("equipment");


        //todo give this to it's controller

    }


    private static void checkSetTextureBlock(String command){
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

        if (GameCommands.getMatcher(command, GameCommands.SET_TEXTURE_BLOCK_CHECK) == null){
            System.out.println("Invalid argument in set texture command!");
            return;
        }


        //todo give this to it's controller

    }

    private static void checkSetTextureArea(String command){
        String x1,y1;
        String x2,y2;
        String type;

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

        String regexForType = GameCommands.getRegexForType();
        Matcher matcherType = Pattern.compile(regexForType).matcher(command);
        if(!matcherType.find()){
            System.out.println("You must give type!");
            return;
        }
        else
            type = matcherType.group("type");

        command = command.replaceAll(matcherType.group().toString().trim(),"");

        if (GameCommands.getMatcher(command, GameCommands.SET_TEXTURE_AREA_CHECK) == null){
            System.out.println("Invalid argument in set texture command!");
            return;
        }


        //todo give this to it's controller

    }


    private static void checkClear(String command){
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


        if (GameCommands.getMatcher(command, GameCommands.CLEAR_CHECK) == null){
            System.out.println("Invalid argument in clear command!");
            return;
        }


        //todo give this to it's controller

    }

    private static void checkDropRock(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String direction = matcher.group("direction");


        //todo give this to it's controller

    }

    private static void checkDropTree(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String type = matcher.group("type");


        //todo give this to it's controller

    }

    private static void checkDropBuilding(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String type = matcher.group("type");


        //todo give this to it's controller

    }

    private static void checkDropUnit(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String type = matcher.group("type");
        String count = matcher.group("count");

        //todo give this to it's controller

    }

    //todo delete after debug
    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        gameMenu.run();
    }
}
