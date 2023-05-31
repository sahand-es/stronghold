package view;

import controller.GameControl;
import model.Database;
import view.enums.AllMenus;
import view.enums.commands.GameCommands;
import view.enums.commands.MainMenuCommands;
import view.enums.messages.GameMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu {
    private static int x;
    private static int y;

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        GameMenu.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        GameMenu.y = y;
    }


    public static void run() {
        Database.setCurrentMenu(AllMenus.GAME_MENU);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        GameControl.setGame(Database.getCurrentGame());
        String command;

        while (true){
            command = scanner.nextLine();
            Matcher matcher;
            if(GameCommands.getMatcher(command,GameCommands.BACK) != null){
                System.out.println("You're now in view.Main Menu!");
                Database.setCurrentMenu(AllMenus.MAIN_MENU);
                return;
            }
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_MENU) != null){
                System.out.println("You're now in Game Menu!");
            }
            else if(GameCommands.getMatcher(command,GameCommands.TRADE) != null){
                Database.setCurrentMenu(AllMenus.TRADE_MENU);
            }
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_POPULARITY_FACTORS) != null){
                System.out.println("Food rate: " + GameControl.getCurrentGovernment().getFoodRate());
                System.out.println("Tax rate: " + GameControl.getCurrentGovernment().getTaxRate());
                System.out.println("Fear rate: " + GameControl.getCurrentGovernment().getFearRate());
            }
            else if(GameCommands.getMatcher(command,GameCommands.SHOW_RESOURCES) != null){
                System.out.println(GameControl.getCurrentGovernment().getResource().toString());
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
                checkFearRate(matcher);
            }
            else if(GameCommands.getMatcher(command,GameCommands.FEAR_RATE_SHOW) != null){
                System.out.println("Tax rate: " + GameControl.getCurrentGovernment().getFearRate());
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
                GameMessages message = GameControl.repair();
                System.out.println(message.message());
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
                GameMessages message = GameControl.disbandUnit();
                System.out.println(message.message());
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
            else if (GameCommands.getMatcher(command,GameCommands.SHOW_UNIT_DATA) != null){
                System.out.println(GameControl.showSelectedUnitDetails());
            }
            else if(GameCommands.getMatcher(command,GameCommands.NEXT_TURN) != null){
                GameControl.nextTurn();
                System.out.println(Database.getCurrentGame().getCurrentGovernment());
            }
            else if(MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MAP) != null){
                checkShowMAp(command);
            }
            else
                System.out.println("My liege, that's an invalid command!");


            switch (Database.getCurrentMenu()){
                case TRADE_MENU:
                    System.out.println("You're now in Trade Menu!");
                    TradeMenu.run();
                    break;

                case MARKET_MENU:
                    System.out.println("You're now in Market Menu!");
                    MarketMenu.run();
                    break;


                case MAP_MENU:
                    System.out.println("You're now in Map Menu!");
                    MapMenu.run(getX(),getY());
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
        String fearRateNumber = matcher.group("fearRateNumber");
        int rate = Integer.parseInt(fearRateNumber);
        GameMessages message = GameControl.checkFearRate(rate);

        if (message.equals(GameMessages.INVALID_RATE)){
            System.out.println("rate is invalid");
        }
        if(message.equals(GameMessages.SUCCESS)){
            System.out.println("fear rate:" + rate);
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
            type = matcherType.group("type").trim();

        command = command.replaceAll(matcherType.group().toString().trim(),"");

        if (GameCommands.getMatcher(command, GameCommands.CREATE_BUILDING_CHECK) == null){
            System.out.println("Invalid argument in create building command!");
            return;
        }

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);
        GameMessages message = GameControl.createBuilding(X,Y,type);
        System.out.println(message.message());
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

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);
        GameMessages message = GameControl.selectBuilding(X,Y);
        System.out.println(message.message());

        if (message.equals(GameMessages.MARKET_MENU)){
            Database.setCurrentMenu(AllMenus.MARKET_MENU);
            GameControl.setSelectedBuilding(null);
        }
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

        int c = Integer.parseInt(count);

        GameMessages message = GameControl.createUnit(type,c);
        System.out.println(message.message());
    }

    private static void checkSelectUnit(String command){
        String x,y,s;

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

        Matcher matcher;
        if ((matcher = GameCommands.getMatcher(command, GameCommands.SELECT_UNIT_CHECK)) == null){
            System.out.println("Invalid argument in select unit command!");
            return;
        }
        s = matcher.group("selectionCount");



        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);
        int S = Integer.parseInt(s);


        GameMessages message = GameControl.selectUnit(X,Y,S);
        System.out.println(message.message());
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

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.moveUnit(X,Y);
        System.out.println(message.message());

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

        int X1 = Integer.parseInt(x1);
        int Y1 = Integer.parseInt(y1);
        int X2 = Integer.parseInt(x2);
        int Y2 = Integer.parseInt(y2);

        GameMessages message = GameControl.patrolUnit(X1,Y1,X2,Y2);
        System.out.println(message.message());
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

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.setSoldierState(state);
        System.out.println(message.message());
    }

    private static void checkAttack(Matcher matcher){
        String x,y;

        x = matcher.group("xNumber");
        y = matcher.group("yNumber");

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.attack(X,Y);
        System.out.println(message.message());

    }

    private static void checkPourOil(Matcher matcher){
        String direction = matcher.group("direction");

        GameMessages message = GameControl.pourOil(direction);
        System.out.println(message.message());
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

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.digTunnel(X,Y);
        System.out.println(message.message());
    }

    private static void checkBuildEquipment(Matcher matcher){
        String equipment = matcher.group("equipment");
        GameMessages message = GameControl.buildSiege(equipment);
        System.out.println(message.message());
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


        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.setTexture(X,Y,type);
        System.out.println(message.message());
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

        int X1 = Integer.parseInt(x1);
        int Y1 = Integer.parseInt(y1);
        int X2 = Integer.parseInt(x2);
        int Y2 = Integer.parseInt(y2);

        GameMessages message = GameControl.setGroupTexture(X1,Y1,X2,Y2,type);
        System.out.println(message.message());
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

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.clearBlock(X,Y);
        System.out.println(message.message());
    }

    private static void checkDropRock(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String direction = matcher.group("direction");


        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.dropRock(X,Y);
        System.out.println(message.message());
    }

    private static void checkDropTree(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String type = matcher.group("type");


        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.dropTree(X,Y);
        System.out.println(message.message());
    }

    private static void checkDropBuilding(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String type = matcher.group("type");

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);

        GameMessages message = GameControl.dropBuilding(X,Y,type);
        System.out.println(message.message());
    }

    private static void checkDropUnit(Matcher matcher){
        String x = matcher.group("xNumber");
        String y = matcher.group("yNumber");
        String type = matcher.group("type");
        String count = matcher.group("count");

        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);
        int c = Integer.parseInt(count);

        GameMessages message = GameControl.dropUnit(X,Y,type,c);
        System.out.println(message.message());
    }


    private static void checkShowMAp(String command){
        String x,y;

        String regexForX = MainMenuCommands.getRegexForX();
        Matcher matcherX = Pattern.compile(regexForX).matcher(command);
        if(!matcherX.find()){
            System.out.println("You must give x!");
            return;
        }
        else
            x = matcherX.group("xNum");

        command = command.replaceAll(matcherX.group().toString().trim(),"");

        String regexForY = MainMenuCommands.getRegexForY();
        Matcher matcherY = Pattern.compile(regexForY).matcher(command);
        if(!matcherY.find()){
            System.out.println("You must give y!");
            return;
        }
        else
            y = matcherY.group("yNum");

        command = command.replaceAll(matcherY.group().toString().trim(),"");

        if (MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MAP_FINAL_CHECK) == null){
            System.out.println("Invalid argument in show map command!");
            return;
        }

        setX(Integer.parseInt(x));
        setY(Integer.parseInt(y));
        Database.setCurrentMenu(AllMenus.MAP_MENU);

    }
}
