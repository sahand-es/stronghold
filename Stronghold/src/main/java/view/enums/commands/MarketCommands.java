package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MarketCommands
{
    BACK("\\s*back\\s*"),
    BUY("\\s*buy\\s+-i\\s+(?<item>.+)\\s+-a\\s+(?<amount>\\d+)\\s*"),
    SELL("\\s*sell\\s+-i\\s+(?<item>.+)\\s+-a\\s+(?<amount>\\d+)\\s*"),
    SHOW_SHOP_LIST("\\s*show\\s+list\\s*");

    final String regex;

    MarketCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , MarketCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
