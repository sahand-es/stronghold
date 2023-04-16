package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands
{
    ;

    String regex;

    ProfileCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , ProfileCommands command)
    {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
