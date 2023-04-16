package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpCommands
{
    ;

    String regex;

    SignUpCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input ,SignUpCommands command)
    {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
