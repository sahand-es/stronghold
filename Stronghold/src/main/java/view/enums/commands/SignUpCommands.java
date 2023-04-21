package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpCommands
{
    EXIT("^\\s*exit\\s*$"),
    CREATE("^user create -u (?<username>.+ )?-p (?<password>\\S+ )?(?<confirmPassword>\\S+ )?-n (?<nickname>.+)?-email(?<email> \\S+)?( -s (?<slogan>.+)?)?$"),
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
}
