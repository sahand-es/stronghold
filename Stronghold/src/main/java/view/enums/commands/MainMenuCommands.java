package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands
{
    EXIT("^\\s*exit\\s*$"),
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    PROFILE_MENU("^\\s*profile\\s+menu\\s*$"),
    GAME_MENU("^\\s*game\\s+menu\\s*$"),
    SHOW_MAP("s"),
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
}
