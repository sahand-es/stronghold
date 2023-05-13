package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands
{
    EXIT("^\\s*exit\\s*$"),
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    PROFILE_MENU("^\\s*profile\\s+menu\\s*$"),
    START_GAME("^\\s*start\\s+game\\s+-w\\s+(?<width>\\d+)\\s+-h\\s(?<height>\\d+)\\s+-n\\s+(?<number>\\d+)\\s*$"),
    SHOW_MAP("^\\s*show\\s+map\\s+.*"),
    SHOW_MAP_FINAL_CHECK("^\\s*show\\s+map\\s+$"),
    X_DIR("-x\\s+(?<xNum>\\S+)"),
    Y_DIR("-y\\s+(?<yNum>\\S+)"),
    LOGOUT("^\\s*user\\s+logout\\s*$"),
    ;

    String regex;

    MainMenuCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , MainMenuCommands command)
    {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public static String getRegexForX(){
        return X_DIR.regex;
    }
    public static String getRegexForY(){
        return Y_DIR.regex;
    }
}
