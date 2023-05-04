package model.units.enums;

import model.resourecs.ResourcesName;

public enum UnitNames {
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
    FIRE_THROWER("Fire Thrower")
    ;
    private final String name;

    UnitNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UnitNames getUnitByName(String name)
    {
        for (UnitNames unitName : UnitNames.values()) {
            if (unitName.name.equalsIgnoreCase(name.trim()))
                return unitName;
        }
        return null;
    }
}
