package model.units.enums;

import org.jetbrains.annotations.NotNull;

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
}
