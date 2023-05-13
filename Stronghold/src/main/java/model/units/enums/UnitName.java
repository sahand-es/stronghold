package model.units.enums;

import model.environment.buildings.enums.BuildingName;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public enum UnitName {
    ARCHER("Archer", "Soldier"),
    CROSSBOWMAN("Crossbowman", "Soldier"),
    SPEARMAN("Spearman", "Soldier"),
    PIKEMAN("Pikeman", "Soldier"),
    MACEMAN("Maceman", "Soldier"),
    SWORDSMAN("Swordsman", "Soldier"),
    KNIGHT("Knight", "Soldier"),
    TUNNELER("Tunneler", "Tunneler"),
    LADDERMAN("Ladderman", "Ladderman"),
    ENGINEER("Engineer", "Engineer"),
    SLAVE("Slave", "Soldier"),
    SLINGER("Slinger", "Soldier"),
    ASSASSIN("Assassin", "Soldier"),
    HORSE_ARCHER("Horse Archer", "Soldier"),
    ARABIAN_SWORDSMAN("Arabian Swordsman", "Soldier"),
    ARABIAN_BOW("Arabian Bow", "Soldier"),
    BLACK_MONK("Black Monk", "Soldier"),
    FIRE_THROWER("Fire Thrower", "Soldier"),
    BATTERING_RAM("Battering Ram", "Soldier"),
    CATAPULT("Catapult", "Soldier"),
    STONE_THROWER("Stone Thrower", "Soldier"),
    SIEGE_TOWER("Siege Tower", "Ladderman")
    ;
    private final String name;
    public final String kind;
    public final static ArrayList<UnitName> barracks = new ArrayList<>(List.of(ARCHER,CROSSBOWMAN,SPEARMAN,PIKEMAN,MACEMAN,SWORDSMAN,KNIGHT));
    public final static ArrayList<UnitName> mercenaryPost = new ArrayList<>(List.of(SLAVE,SLINGER,ASSASSIN,HORSE_ARCHER,ARABIAN_BOW,ARABIAN_SWORDSMAN,FIRE_THROWER));
    public final static ArrayList<UnitName> engineerGuild = new ArrayList<>(List.of(ENGINEER,LADDERMAN,TUNNELER));
    public final static ArrayList<UnitName> church = new ArrayList<>(List.of(BLACK_MONK));
    public final static ArrayList<UnitName>  siegeBuilds = new ArrayList<>(List.of(BATTERING_RAM, CATAPULT, STONE_THROWER, SIEGE_TOWER));


    UnitName(String name, String kind) {
        this.name = name;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public static UnitName getUnitByName(String name) {
        for (UnitName unitName : UnitName.values()) {
            if (unitName.name.equalsIgnoreCase(name.trim()))
                return unitName;
        }
        throw new RuntimeException();
    }

    public static boolean isValidName(String name) {
        for (UnitName value : UnitName.values()) {
            if (value.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public static boolean isValidSiegeBuild(String string) {
        for (UnitName siegeBuild : siegeBuilds) {
            if (siegeBuild.getName().equalsIgnoreCase(string))
                return true;
        }
        return false;
    }

    public static boolean canThisBuildingMakeIt(BuildingName buildingName, UnitName unitName) {
//        ToDo: add sieges
        switch (buildingName) {
            case BARRACKS: {
                return barracks.contains(unitName);
            }
            case MERCENARY_POST: {
                return mercenaryPost.contains(unitName);
            }
            case ENGINEER_GUILD: {
                return engineerGuild.contains(unitName);
            }
            case CHURCH:
            case CATHEDRAL:
                return church.contains(unitName);

        }
        return false;
    }
    public static ArrayList<UnitName> getUnits(BuildingName name){
        switch (name){
            case BARRACKS:
                return barracks;

            case MERCENARY_POST:
                return mercenaryPost;

            case ENGINEER_GUILD:
                return engineerGuild;
            case CHURCH:
            case CATHEDRAL:
                return church;

            default:
                return null;
        }
    }
}
