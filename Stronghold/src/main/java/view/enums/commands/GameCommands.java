package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    BACK("^\\s*back\\s*$"),
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    SHOW_POPULARITY("^\\s*show\\s+popularity\\s*$"),
    SHOW_POPULARITY_FACTORS("^\\s*show\\s+popularity\\s+factors\\s*$"),
    SHOW_FOOD("^\\s*show\\s+food\\s+list\\s*$"),
    FOOD_RATE("^\\s*food\\s+rate\\s+-r\\s+(?<rateNumber>\\d+)\\s*$"),
    FOOD_RATE_SHOW("^\\s*food\\s+rate\\s+show\\s*$"),
    TAX_RATE("^\\s*tax\\s+rate\\s+-r\\s+(?<taxNumber>\\d+)\\s*$"),
    TAX_RATE_SHOW("^\\s*tax\\s+rate\\s+show\\s*$"),
    FEAR_RATE("^\\s*fear\\s+rate\\s+-r\\s+(?<fearNumber>\\d+)\\s*$"),
    FEAR_RATE_SHOW("^\\s*fear\\s+rate\\s+show\\s*$"),
    CREATE_BUILDING("^\\s*create\\s+building\\s+.*"),
    CREATE_BUILDING_CHECK("^\\s*create\\s+building\\s+$"),
    SELECT_BUILDING("^\\s*select\\s+building\\s+.*"),
    SELECT_BUILDING_CHECK("^\\s*select\\s+building\\s+$"),
    CREATE_UNIT("^\\s*createunit\\s+.*"),
    CREATE_UNIT_CHECK("^\\s*createunit\\s+$"),
    X_DIR("-x\\s+(?<xNum>\\d+)"),
    Y_DIR("-y\\s+(?<yNum>\\d+)"),
    TYPE("-t\\s+(?<type>\\S+)"),
    COUNT("-c\\s+(?<count>\\d+)"),
    REPAIR("^\\s*repair\\s*$"),
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

    public static String getRegexForX(){
        return X_DIR.regex;
    }
    public static String getRegexForY(){
        return Y_DIR.regex;
    }
    public static String getRegexForType(){
        return TYPE.regex;
    }
    public static String getRegexForCount(){
        return COUNT.regex;
    }
}
