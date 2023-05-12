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
    FIRE_THROWER("Fire Thrower");
    private final String name;
    public final static ArrayList<UnitName> Barracks = new ArrayList<>(List.of(ARCHER,CROSSBOWMAN,SPEARMAN,PIKEMAN,MACEMAN,SWORDSMAN,KNIGHT));
    public final static ArrayList<UnitName> MercenaryPost = new ArrayList<>(List.of(SLAVE,SLINGER,ASSASSIN,HORSE_ARCHER,ARABIAN_BOW,ARABIAN_SWORDSMAN,FIRE_THROWER));
    public final static ArrayList<UnitName> EngineerGuild = new ArrayList<>(List.of(ENGINEER,LADDERMAN,TUNNELER));

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

    public static ArrayList<UnitName> getUnits(BuildingName name){
        switch (name){
            case BARRACKS:
                return Barracks;

            case MERCENARY_POST:
                return MercenaryPost;

            case ENGINEER_GUILD:
                return EngineerGuild;

            default:
                return null;
        }
    }
}
