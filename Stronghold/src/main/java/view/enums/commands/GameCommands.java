package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    BACK("^\\s*back\\s*$"),
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    TRADE("^\\s*trade\\s+menu\\s*$")
    ;

    String regex;

    GameCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , GameCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
