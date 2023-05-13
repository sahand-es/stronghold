package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    BACK("^\\s*back\\s*$"),
    SHOW_MENU("^\\s*show\\s+menu\\s*$"),
    SHOW_POPULARITY("^\\s*show\\s+popularity\\s*$"),
    SHOW_POPULARITY_FACTORS("^\\s*show\\s+popularity\\s+factors\\s*$"),
    SHOW_FOOD("^\\s*show\\s+food\\s+list\\s*$"),
    FOOD_RATE("^\\s*food\\s+rate\\s+-r\\s+(?<foodRateNumber>\\d+)\\s*$"),
    FOOD_RATE_SHOW("^\\s*food\\s+rate\\s+show\\s*$"),
    TAX_RATE("^\\s*tax\\s+rate\\s+-r\\s+(?<taxRateNumber>\\d+)\\s*$"),
    TAX_RATE_SHOW("^\\s*tax\\s+rate\\s+show\\s*$"),
    FEAR_RATE("^\\s*fear\\s+rate\\s+-r\\s+(?<fearRateNumber>\\d+)\\s*$"),
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
    SELECT_UNIT("^\\s*select\\s+unit\\s+.*"),
    SELECT_UNIT_CHECK("^\\s*select\\s+unit\\s+$"),
    MOVE_UNIT("^\\s*move\\s+unit\\s+to\\s+.*"),
    MOVE_UNIT_CHECK("^\\s*move\\s+unit\\s+to\\s+$"),
    PATROL_UNIT("^\\s*patrol\\s+unit\\s+.*"),
    PATROL_UNIT_CHECK("^\\s*patrol\\s+unit\\s+$"),
    X1_DIR("-x1\\s+(?<x1Num>\\d+)"),
    Y1_DIR("-y1\\s+(?<y1Num>\\d+)"),
    X2_DIR("-x2\\s+(?<x2Num>\\d+)"),
    Y2_DIR("-y2\\s+(?<y2Num>\\d+)"),
    SET("^\\s*set\\s+.*"),
    SET_CHECK("^\\s*set\\s+$"),
    STATE("-s\\s+(?<state>(standing|defensive|offensive))"),
    ATTACK("\\s*attack\\s+-e\\s+(?<xNumber>\\d+)\\s+(?<yNumber>\\d+)\\s*"),
    POUR_OIL("^\\s*pour\\s+oil\\s+-d\\s+(?<direction>\\S+)\\s*$"),
    DIG("^\\s*dig\\s+tunnel\\s+.*"),
    DIG_CHECK("\\s*dig\\s+tunnel\\s+$"),
    BUILD_EQUIPMENT("^\\s*build\\s+-q\\s+(?<equipment>\\S+)\\s*$"),
    DISBAND("^\\s*disband\\s*$"),
    NEXT_TURN("^\\s*next\\s*turn\\s*$"),
    TRADE("^\\s*trade\\s+menu\\s*$"),

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
    public static String getRegexForX1(){
        return X1_DIR.regex;
    }
    public static String getRegexForX2(){
        return X2_DIR.regex;
    }
    public static String getRegexForY(){
        return Y_DIR.regex;
    }
    public static String getRegexForY1(){
        return Y1_DIR.regex;
    }
    public static String getRegexForY2(){
        return Y2_DIR.regex;
    }
    public static String getRegexForType(){
        return TYPE.regex;
    }
    public static String getRegexForCount(){
        return COUNT.regex;
    }
    public static String getRegexState(){
        return STATE.regex;
    }
}
