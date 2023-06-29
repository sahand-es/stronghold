package model.units.enums;

import model.environment.buildings.enums.BuildingName;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public enum UnitName {
    ARCHER("Archer", "Soldier", "archer.png"),
    CROSSBOWMAN("Crossbowman", "Soldier", "crossbowman.png"),
    SPEARMAN("Spearman", "Soldier", "spearman.png"),
    PIKEMAN("Pikeman", "Soldier", "pikeman.png"),
    MACEMAN("Maceman", "Soldier", "maceman.png"),
    SWORDSMAN("Swordsman", "Soldier", "swordsman.png"),
    KNIGHT("Knight", "Soldier", "knight.png"),
    TUNNELER("Tunneler", "Tunneler", "tunneler.png"),
    LADDERMAN("Ladderman", "Ladderman", "ladderman.png"),
    ENGINEER("Engineer", "Engineer", "engineer.png"),
    SLAVE("Slave", "Soldier", "slave.png"),
    SLINGER("Slinger", "Soldier", "slinger.png"),
    ASSASSIN("Assassin", "Soldier", "assassin.png"),
    HORSE_ARCHER("Horse Archer", "Soldier", "horse archer.png"),
    ARABIAN_SWORDSMAN("Arabian Swordsman", "Soldier", "arabian swordsman.png"),
    ARABIAN_BOW("Arabian Bow", "Soldier", "arabian bow.png"),
    BLACK_MONK("Black Monk", "Soldier", "blackmonk.png"),
    FIRE_THROWER("Fire Thrower", "Soldier", "fire thrower.png"),
    BATTERING_RAM("Battering Ram", "Soldier", "battering ram.png"),
    CATAPULT("Catapult", "Soldier", "catapult.png"),
    STONE_THROWER("Stone Thrower", "Soldier", "stone thrower.png"),
    SIEGE_TOWER("Siege Tower", "Ladderman", "siege tower.png")
    ;
    private final String name;
    public final String kind;
    private final String imagePath;
    public final static ArrayList<UnitName> barracks = new ArrayList<>(List.of(ARCHER,CROSSBOWMAN,SPEARMAN,PIKEMAN,MACEMAN,SWORDSMAN,KNIGHT));
    public final static ArrayList<UnitName> mercenaryPost = new ArrayList<>(List.of(SLAVE,SLINGER,ASSASSIN,HORSE_ARCHER,ARABIAN_BOW,ARABIAN_SWORDSMAN,FIRE_THROWER));
    public final static ArrayList<UnitName> engineerGuild = new ArrayList<>(List.of(ENGINEER,LADDERMAN,TUNNELER));
    public final static ArrayList<UnitName> church = new ArrayList<>(List.of(BLACK_MONK));
    public final static ArrayList<UnitName>  siegeBuilds = new ArrayList<>(List.of(BATTERING_RAM, CATAPULT, STONE_THROWER, SIEGE_TOWER));


    UnitName(String name, String kind, String imagePath) {
        this.name = name;
        this.kind = kind;
        this.imagePath = imagePath;
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
            case SIEGE_TENT:
                return siegeBuilds;

            default:
                return null;
        }
    }
    public boolean isSoldier() {
        return this.kind.equalsIgnoreCase("soldier");
    }

    public String getImagePath() {
        return "file:src/main/resources/images/soldiers/" + imagePath;
    }
}
