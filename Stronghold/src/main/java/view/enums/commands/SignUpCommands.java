package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpCommands
{
    EXIT("^\\s*exit\\s*$"),
    BACK("^\\s*back\\s*$"),
    CREATE("^(\\s+)?user\\s+create\\s+.+"),
    FINALCREATECHECK("^(\\s+)?user\\s+create(\\s+)?$"),
    ARGUMENT("((\\s+)?-(?<argumentSpace>\\w+)\\s+\"(?<firstStringSpace>[^-\"]+)\"(\\s+)?(?:\\s+(\")?(?<secondStringSpace>[^-\"]+))?)(?:\\s+(?<secondStringsSpaceNON>\\S+))?|((\\s+)?-(?<argument>\\w+)\\s+(?<firstString>[^- ]+)(?:\\s+(?<secondString>[^- ]+))?)"),
    QUESTION("^(\\s+)?question\\s+pick\\s+.+"),
    QUESTION_ARGUMENT("((\\s+)?-(?<argument>\\w+))((\\s+\"(?<stringSpace>[^-\"]+)\")|(\\s+)?(?<string>[^- ]+))"),
    FINALQUESTIONCHECK("^(\\s+)?question\\s+pick(\\s+)?$"),
    NUMBER_FORMAT("1|2|3"),
    ;

    String regex;

    SignUpCommands(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input ,SignUpCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public static String getRegexARGUMENT(){
        return ARGUMENT.regex;
    }

    public static String getRegexQUESTIONARGUMENT(){
        return QUESTION_ARGUMENT.regex;
    }

}
