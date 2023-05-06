package model.environment.buildings.enums;

public enum BuildingName {
// Gate:
    SMALL_STONE_GATEHOUSE("Small Stone Gatehouse"),
    LARGE_STONE_GATEHOUSE("Large Stone Gatehouse"),

// Bridge:
    DRAWBRIDGE("Drawbridge"),

// Defencive:
    LOOKOUT_TOWER("Lookout Tower"),
    PERIMETER_TOWER("Perimeter Tower\n"),
    DEFENCE_TURRET("Defence Turret\n"),
    SQUARE_TOWER("Square Tower\n"),
    ROUND_TOWER("Round Tower\n"),
    CAGED_WARDOGS("Caged War Dogs\n"),

// Storage:
    ARMOURY("Armoury\n"),
    GRANARY("Granary\n"),
    STOCKPILE("Stockpile\n"),

// Unit Maker:
    BARRACKS("Barracks\n"),
    MERCENARY_POST("Mercenary Post\n"),
    ENGINEER_GUILD("Engineer Guild\n"),
    TUNNELER_GUILD("Tunneler Guild\n"),

// Trap:
    KILLING_PIT("Killing Pit\n"),
    PITCH_DITCH("Pitch Ditch\n"),

// Resource Maker:
    OIL_SMELTER("Oil Smelter\n"),
    BAKERY("Bakery\n"),
    BREWER("Brewer\n"),
    MILL("Mill\n"),
    ARMOURER("Armourer\n"),
    BLACKSMITH("Blacksmith\n"),
    FLETCHER("Fletcher\n"),
    POLETURNER("Poleturner\n"),

// Extractor:
    APPLE_ORCHARD("Apple Orchard\n"),
    DAIRY_FARMER("Dairy Farmer\n"),
    HOPS_FARMER("Hops Farmer\n"),
    HUNTERS_POST("Hunter Post\n"),
    WHEAT_FARMER("Wheat Farmer\n"),
    IRON_MINE("Iron Mine\n"),
    PITCH_RIG("Pitch Rig\n"),
    QUARRY("Quarry\n"),
    WOODCUTTER("Woodcutter\n"),
    STABLE("Stable\n"),

// Church:
    CHURCH("Church\n"),
    CATHEDRAL("Cathedral\n"),

// Other:
    SEIGE_TENT("Siege Tent\n"),
    INN("Inn\n"),
    MARKET("Market\n"),
    HOVEL("Hovel\n"),
    TUNNEL_ENTRANCE("Tunnel Entrance\n"),
    OX_TETHER("Ox Tether\n"),
    ;
    final String name;

    BuildingName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
