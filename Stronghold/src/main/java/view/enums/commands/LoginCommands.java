package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands
{
    ;

    String regex;

    LoginCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , LoginCommands command)
    {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

