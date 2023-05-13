package model.units.enums;

import model.environment.buildings.enums.BuildingName;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public enum UnitName {
    ARCHER("Archer"),
    CROSSBOWMAN("Crossbowman"),
    SPEARMAN("Spearman"),
    PIKEMAN("Pikeman"),
    MACEMAN("Maceman"),
    SWORDSMAN("Swordsman"),
    KNIGHT("Knight"),
    TUNNELER("Tunneler"),
    LADDERMAN("Ladderman"),
    ENGINEER("Engineer"),
    SLAVE("Slave"),
    SLINGER("Slinger"),
    ASSASSIN("Assassin"),
    HORSE_ARCHER("Horse Archer"),
    ARABIAN_SWORDSMAN("Arabian Swordsman"),
    ARABIAN_BOW("Arabian Bow"),
    BLACK_MONK("Black Monk"),
    FIRE_THROWER("Fire Thrower"),
    BATTERING_RAM("Battering Ram"),
    CATAPULT("Catapult"),
    STONE_THROWER("Stone Thrower"),
    SIEGE_TOWER("Siege Tower")
    ;
    private final String name;
    public final static ArrayList<UnitName> barracks = new ArrayList<>(List.of(ARCHER,CROSSBOWMAN,SPEARMAN,PIKEMAN,MACEMAN,SWORDSMAN,KNIGHT));
    public final static ArrayList<UnitName> mercenaryPost = new ArrayList<>(List.of(SLAVE,SLINGER,ASSASSIN,HORSE_ARCHER,ARABIAN_BOW,ARABIAN_SWORDSMAN,FIRE_THROWER));
    public final static ArrayList<UnitName> engineerGuild = new ArrayList<>(List.of(ENGINEER,LADDERMAN,TUNNELER));


    UnitName(String name) {
        this.name = name;
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

    public static boolean canThisBuildingMakeIt(BuildingName buildingName, UnitName unitName) {
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

            default:
                return null;
        }
    }
}
