package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands {
    LOGIN("^\\s*user\\s+login\\s+.+"),
    USER_ARGUMENT("-u\\s+(\"(?<usernameSpace>[^-]+)\"|(?<username>[^-\\s]+))?"),
    PASS_ARGUMENT("-p\\s+((\"(?<passwordSpace>[^-]+)\")|(?<password>[^-\\s]+))?"),
    FINAL_LOGIN_CHECK("^(\\s+)?user\\s+login(\\s+)?$"),
    STAY_LOGGED_IN("--stay-logged-in"),
    FORGOT_PASSWORD("^\\s*forgot\\s+my\\s+password\\s+-u(\\s+)?((?<username>\\S+)|\"(?<usernameSpace>.+)\")?\\s*$"),
    EXIT("^\\s*exit\\s*$"),
    BACK("^\\s*back\\s*$"),
    SIGNUP("^\\s*signup\\s+menu\\s*$")
    ;

    String regex;

    LoginCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , LoginCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public static String getRegexUSER(){
        return USER_ARGUMENT.regex;
    }
    public static String getRegexPASS(){
        return PASS_ARGUMENT.regex;
    }
    public static String getRegexStayLoggedIn(){
        return STAY_LOGGED_IN.regex;
    }

}

