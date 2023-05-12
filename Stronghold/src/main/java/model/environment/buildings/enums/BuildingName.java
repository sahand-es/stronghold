package model.environment.buildings.enums;

import org.jetbrains.annotations.NotNull;

public enum BuildingName {
    // Gate:
    SMALL_STONE_GATEHOUSE("Small Stone Gatehouse"),
    LARGE_STONE_GATEHOUSE("Large Stone Gatehouse"),

    // Bridge:
    DRAWBRIDGE("Drawbridge"),

    // Defencive:
    LOOKOUT_TOWER("Lookout Tower"),
    PERIMETER_TOWER("Perimeter Tower"),
    DEFENCE_TURRET("Defence Turret"),
    SQUARE_TOWER("Square Tower"),
    ROUND_TOWER("Round Tower"),
    CAGED_WAR_DOGS("Caged War Dogs"),

    // Storage:
    ARMOURY("Armoury"),
    GRANARY("Granary"),
    STOCKPILE("Stockpile"),

    // Unit Maker:
    BARRACKS("Barracks"),
    MERCENARY_POST("Mercenary Post"),
    ENGINEER_GUILD("Engineer Guild"),
    TUNNELER_GUILD("Tunneler Guild"),

    // Trap:
    KILLING_PIT("Killing Pit"),
    PITCH_DITCH("Pitch Ditch"),

    // Resource Maker:
    OIL_SMELTER("Oil Smelter"),
    BAKERY("Bakery"),
    BREWER("Brewer"),
    MILL("Mill"),
    ARMOURER("Armourer"),
    BLACKSMITH("Blacksmith"),
    FLETCHER("Fletcher"),
    POLETURNER("Poleturner"),

    // Extractor:
    APPLE_ORCHARD("Apple Orchard"),
    DAIRY_FARMER("Dairy Farmer"),
    HOPS_FARMER("Hops Farmer"),
    HUNTERS_POST("Hunter Post"),
    WHEAT_FARMER("Wheat Farmer"),
    IRON_MINE("Iron Mine"),
    PITCH_RIG("Pitch Rig"),
    QUARRY("Quarry"),
    WOODCUTTER("Woodcutter"),
    STABLE("Stable"),

    // Church:
    CHURCH("Church"),
    CATHEDRAL("Cathedral"),

    // Other:
    SIEGE_TENT("Siege Tent"),
    INN("Inn"),
    MARKET("Market"),
    HOVEL("Hovel"),
    TUNNEL_ENTRANCE("Tunnel Entrance"),
    OX_TETHER("Ox Tether"),
    SHORT_WALL("Short Wall"),
    TALL_WALL("Tall Wall"),
    STAIR("Stair"),
    CASTLE("Castle"),


    ;
    private final String name;

    BuildingName(String name) {
        this.name = name;
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
}
