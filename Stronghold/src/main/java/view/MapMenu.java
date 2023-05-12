package view;

import model.Application;
import utility.CheckFunctions;
import utility.DataManager;
import view.enums.AllMenus;
import view.enums.commands.LoginCommands;
import view.enums.commands.MainMenuCommands;
import view.enums.commands.MapMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapMenu {
    private static int mapX,mapY;

    public static int getMapX() {
        return mapX;
    }

    public static void setMapX(int mapX) {
        MapMenu.mapX = mapX;
    }

    public static int getMapY() {
        return mapY;
    }

    public static void setMapY(int mapY) {
        MapMenu.mapY = mapY;
    }

    public static void run(int x , int y){
        setMapX(x);
        setMapY(y);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;

        while(true){

            System.out.println("XY: " + getMapX() + " " + getMapY());
            //todo literally show the fucking map!

            command = scanner.nextLine();

            if(MapMenuCommands.getMatcher(command, MapMenuCommands.EXIT) != null){
                System.out.println("You're in Main Menu!");
                Application.setCurrentMenu(AllMenus.MAIN_MENU);
                return;
            }
            else if(MapMenuCommands.getMatcher(command,MapMenuCommands.MAP) != null){
                getCoordinates(command);
            }
            //todo show detail
            else if(MapMenuCommands.getMatcher(command,MapMenuCommands.SHOW_MENU) != null){
                System.out.println("You're in Map Menu!");
            }
            else
                System.out.println("My liege, that's an invalid command!");


            switch (Application.getCurrentMenu()){
                case MAIN_MENU:
                    return;
                default:
                    break;
            }
        }

    }


    private static void getCoordinates(String command){
        int right = 0, left = 0, up = 0, down = 0;

        String regexRight = MapMenuCommands.getRegexRight();
        Matcher rightMatcher = Pattern.compile(regexRight).matcher(command);
        while (rightMatcher.find()){
            String temp = rightMatcher.group("rightNumber");
            temp = (temp == null) ? String.valueOf(1) : temp;
            right += Integer.parseInt(temp);
            command = command.replaceFirst(rightMatcher.group().toString(),"");
        }

        String regexLeft = MapMenuCommands.getRegexLeft();
        Matcher leftMatcher = Pattern.compile(regexLeft).matcher(command);
        while (leftMatcher.find()){
            String temp = leftMatcher.group("leftNumber");
            temp = (temp == null) ? String.valueOf(1) : temp;
            left += Integer.parseInt(temp);
            command = command.replaceFirst(leftMatcher.group().toString().trim(),"");
        }

        String regexUp = MapMenuCommands.getRegexUP();
        Matcher upMatcher = Pattern.compile(regexUp).matcher(command);
        while (upMatcher.find()){
            String temp = upMatcher.group("upNumber");
            temp = (temp == null) ? String.valueOf(1) : temp;
            up += Integer.parseInt(temp);
            command = command.replaceFirst(upMatcher.group().toString().trim(),"");
        }

        String regexDown = MapMenuCommands.getRegexDown();
        Matcher downMatcher = Pattern.compile(regexDown).matcher(command);
        while (downMatcher.find()){
            String temp = downMatcher.group("downNumber");
            temp = (temp == null) ? String.valueOf(1) : temp;
            down += Integer.parseInt(temp);
            command = command.replaceFirst(downMatcher.group().toString().trim(),"");
        }

        if (MapMenuCommands.getMatcher(command,MapMenuCommands.MAP_FINAL_CHECK) == null){
            System.out.println("Invalid argument in direction command!");
        }
        else{
            setMapX(getMapX() + right - left);
            setMapY(getMapY() + up - down);
        }
    }

}
