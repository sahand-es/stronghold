package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpCommands
{
    EXIT("^\\s*exit\\s*$"),
    CREATE("^(\\s+)?user\\s+create\\s+.+"),
    ARGUMENT("(\\s+-(?<argumentSpace>\\w+)\\s+\"(?<firstStringSpace>[^-\"]+)\"(?:\\s+(\")?(?<secondStringSpace>[^-\"]+))?)(?:\\s+(?<secondStringsSpaceNON>\\S+))?|(\\s+-(?<argument>\\w+)\\s+(?<firstString>[^- ]+)(?:\\s+(?<secondString>[^- ]+))?)"),
    QUESTION("^question pick -q (?<questionNumber>\\d) -a (?<answer>\\S+) -c (?<answerConfirm>\\S+)$"),
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

    public static String getRegexARGUMENT(){
        return ARGUMENT.regex;
    }

}
