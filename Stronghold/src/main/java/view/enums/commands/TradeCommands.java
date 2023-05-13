package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {

    BACK("^\\s*back\\s*$"),

    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    TRADE("^(\\s+)?trade\\s+request\\s+.+"),
    FINAL_TRADE_CHECK("^(\\s+)?trade\\s+request(\\s+)?$"),
    ARGUMENT("((\\s+)?-(?<argumentSpace>\\w+)\\s+\\\"(?<firstStringSpace>[^-\\\"]+)\\\"(\\s+)?)" +
            "|((\\s+)?-(?<argument>\\w+)\\s+(?<firstString>[^- ]+)?)"),


    ;

    String regex;

    TradeCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , TradeCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public static String getRegexARGUMENT(){
        return ARGUMENT.regex;
    }

}
