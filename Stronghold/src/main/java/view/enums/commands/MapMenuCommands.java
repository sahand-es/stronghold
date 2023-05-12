package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    BACK("^\\s*back\\s*$"),

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
}
