package model.environment.buildings.enums;

import org.jetbrains.annotations.NotNull;

public enum BuildingName {
    // Gate:
    SMALL_STONE_GATEHOUSE("Small Stone Gatehouse", "Gate"),
    LARGE_STONE_GATEHOUSE("Large Stone Gatehouse", "Gate"),

    // Bridge:
    DRAWBRIDGE("Drawbridge", "Bridge"),

    // Defencive:
    LOOKOUT_TOWER("Lookout Tower", "Defencive"),
    PERIMETER_TOWER("Perimeter Tower", "Defencive"),
    DEFENCE_TURRET("Defence Turret", "Defencive"),
    SQUARE_TOWER("Square Tower", "Defencive"),
    ROUND_TOWER("Round Tower", "Defencive"),
    CAGED_WAR_DOGS("Caged War Dogs", "Defencive"),

    // Storage:
    ARMOURY("Armoury", "Storage"),
    GRANARY("Granary", "Storage"),
    STOCKPILE("Stockpile", "Storage"),

    // Unit Maker:
    BARRACKS("Barracks", "Unit Maker"),
    MERCENARY_POST("Mercenary Post", "Unit Maker"),
    ENGINEER_GUILD("Engineer Guild", "Unit Maker"),
    TUNNELER_GUILD("Tunneler Guild", "Unit Maker"),
    SIEGE_TENT("Siege Tent", "Unit Maker"),

    // Trap:
    KILLING_PIT("Killing Pit", "Trap"),
    PITCH_DITCH("Pitch Ditch", "Trap"),

    // Resource Maker:
    OIL_SMELTER("Oil Smelter", "Extractor"),
    BAKERY("Bakery", "Extractor"),
    BREWER("Brewer", "Extractor"),
    MILL("Mill", "Extractor"),
    ARMOURER("Armourer", "Extractor"),
    BLACKSMITH("Blacksmith", "Extractor"),
    FLETCHER("Fletcher", "Extractor"),
    POLETURNER("Poleturner", "Extractor"),

    // Extractor:
    APPLE_ORCHARD("Apple Orchard", "Extractor"),
    DAIRY_FARMER("Dairy Farmer", "Extractor"),
    HOPS_FARMER("Hops Farmer", "Extractor"),
    HUNTERS_POST("Hunter Post", "Extractor"),
    WHEAT_FARMER("Wheat Farmer", "Extractor"),
    IRON_MINE("Iron Mine", "Extractor"),
    PITCH_RIG("Pitch Rig", "Extractor"),
    QUARRY("Quarry", "Extractor"),
    WOODCUTTER("Woodcutter", "Extractor"),
    STABLE("Stable", "Extractor"),

    // Church:
    CHURCH("Church", "Church"),
    CATHEDRAL("Cathedral", "Church"),

    // Other:
    INN("Inn", "Inn"),
    MARKET("Market", "Shop"),
    HOVEL("Hovel", "House"),
    TUNNEL_ENTRANCE("Tunnel Entrance", "Building"),
    OX_TETHER("Ox Tether", "Building"),
    SHORT_WALL("Short Wall", "Building"),
    TALL_WALL("Tall Wall", "Building"),
    STAIR("Stair", "Building"),
    CASTLE("Castle", "Castle"),


    ;
    private final String name;
    public final String kind;

    BuildingName(String name, String kind) {
        this.name = name;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public static BuildingName getBuildingNameByName(String name) {
        for (BuildingName buildingName : BuildingName.values()) {
            if (buildingName.getName().equalsIgnoreCase(name))
                return buildingName;
        }
        throw new RuntimeException();
    }
    public static boolean isValidName(String name) {
        for (BuildingName val : values()) {
            if (val.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
}
