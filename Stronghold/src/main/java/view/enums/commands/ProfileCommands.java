package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {


    CHANGE_USERNAME("^profile change -u (?<username>.+)$"),
    CHANGE_NICKNAME("^profile change -n (?<nickname>.+)$"),
    CHANGE_PASSWORD("^profile change password -o (?<oldPassword>\\S+) -n (?<newPassword>\\S+)$"),
    CHANGE_EMAIL("^profile change -e (?<email>\\S+)$"),
    CHANGE_SLOGAN("^profile change slogan -s (?<slogan>.+)$"),
    REMOVE_SLOGAN("^profile remove slogan$"),
    DISPLAY_HIGH_SCORE("^profile display highscore$"),
    DISPLAY_RANK("^profile display rank$"),
    DISPLAY_SLOGAN("^profile display slogan$"),
    DISPLAY_ALL("^profile display$")
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
