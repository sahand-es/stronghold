package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    EXIT("^\\s*exit\\s*$"),
    MAP("^\\s*map\\s.+"),
    MAP_FINAL_CHECK("^\\s*map(\\s+)?"),
    RIGHT("(?<rightNumber>\\d+)?(\\s+)?right"),
    LEFT("(?<leftNumber>\\d+)?(\\s+)?left"),
    UP("(?<upNumber>\\d+)?(\\s+)?up"),
    DOWN("(?<downNumber>\\d+)?(\\s+)?down"),
    ;

    String regex;

    MapMenuCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , MapMenuCommands command)
    {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public static String getRegexRight(){
        return RIGHT.regex;
    }
    public static String getRegexLeft(){
        return LEFT.regex;
    }
    public static String getRegexUP(){
        return UP.regex;
    }
    public static String getRegexDown(){
        return DOWN.regex;
    }
}
