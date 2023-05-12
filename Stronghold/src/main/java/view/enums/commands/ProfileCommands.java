package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {


    CHANGE_USERNAME("^\\s*profile\\s+change\\s+-u\\s+((\"(?<usernameSpace>.+)\")|(?<username>\\S+))$"),
    CHANGE_NICKNAME("^\\s*profile\\s+change\\s+-n\\s+((\"(?<nicknameSpace>.+)\")|(?<nickname>\\S+))$"),
    CHANGE_PASSWORD("^\\s*profile\\s+change\\s+password\\s*$"),
    CHANGE_PASSWORD_OLD("-o\\s+((\"(?<passwordSpace>[^-]+)\")|(?<password>[^-\\s]+))?"),
    CHANGE_PASSWORD_NEW("-n\\s+((\"(?<passwordSpace>[^-]+)\")|(?<password>[^-\\s]+))?"),
    CHANGE_EMAIL("^\\s*profile\\s+change\\s+-e\\s+((\"(?<emailSpace>.+)\")|(?<email>\\S+))$"),
    CHANGE_SLOGAN("^\\s*profile\\s+change\\s+slogan\\s+-s\\s+((\"(?<passwordSpace>.+)\")|(?<password>\\S+))$"),
    REMOVE_SLOGAN("^\\s*profile\\s+remove\\s+slogan\\s*$"),
    DISPLAY_HIGH_SCORE("^\\s*profile\\s+display\\s+highscore\\s*$"),
    DISPLAY_RANK("^\\s*profile\\s+display\\s+rank\\s*$"),
    DISPLAY_SLOGAN("^\\s*profile\\s+display\\s+slogan\\s*$"),
    DISPLAY_ALL("^\\s*profile\\s+display\\s*$"),
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    BACK("^\\s*back\\s*$"),
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
