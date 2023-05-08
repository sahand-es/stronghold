package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands {
    LOGIN("^\\s*user\\s+login\\s+"),
    USER_ARGUMENT("-u\\s+((?<username>\\S+)|\"(?<usernameSpace>.+)\")?"),
    PASS_ARGUMENT("-p\\s+((?<password>\\S+)|\"(?<passwordSpace>.+)\")?"),
    STAY_LOGGED_IN("--stay-logged-in"),
    FORGOT_PASSWORD("^\\s*forgot\\s+my\\s+password\\s+-u\\s+((?<username>\\S+)|\"(?<usernameSpace>.+)\")?\\s*$"),
    EXIT("^\\s*exit\\s*$"),
    SIGNUP("^\\s*signup\\s+menu\\s*$")
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

