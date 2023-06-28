package model.environment.buildings.enums;

import org.jetbrains.annotations.NotNull;

public enum BuildingName {
    // Gate:
    SMALL_STONE_GATEHOUSE("Small Stone Gatehouse", "Gate", "small-stone-gatehouse.png"),
    LARGE_STONE_GATEHOUSE("Large Stone Gatehouse", "Gate", "large-stone-gatehouse.png"),

    // Bridge:
    DRAWBRIDGE("Drawbridge", "Bridge","drawbridge.png"),

    // Defencive:
    PERIMETER_TOWER("Perimeter Tower", "Defencive", "perimeter-tower.png"),
    DEFENCE_TURRET("Defence Turret", "Defencive", "defence-turret.png"),
    SQUARE_TOWER("Square Tower", "Defencive", "square-tower.png"),
    ROUND_TOWER("Round Tower", "Defencive", "round-tower.png"),
    CAGED_WAR_DOGS("Caged War Dogs", "Defencive", "caged-war-dogs.png"),
    LOOKOUT_TOWER("Lookout Tower", "Defencive", "lookout-tower.png"),

    // Storage:
    ARMOURY("Armoury", "Storage", "armoury.png"),
    GRANARY("Granary", "Storage", "granary.gif"),
    STOCKPILE("Stockpile", "Storage", "stockpile.png"),

    // Unit Maker:
    BARRACKS("Barracks", "Unit Maker", "barracks.png"),
    MERCENARY_POST("Mercenary Post", "Unit Maker", "mercenary-post.png"),
    ENGINEER_GUILD("Engineer Guild", "Unit Maker", "engineer-guild.gif"),
    SIEGE_TENT("Siege Tent", "Unit Maker", "siege-tent.png"),

    // Trap:
    KILLING_PIT("Killing Pit", "Trap", "killing-pit.png"),
    PITCH_DITCH("Pitch Ditch", "Trap", "pitch-ditch.png"),

    // Resource Maker:
    POLETURNER("Poleturner", "Extractor", "poleturner.gif"),
    ARMOURER("Armourer", "Extractor","armourer.gif"),
    BLACKSMITH("Blacksmith", "Extractor", "blacksmith.gif"),
    FLETCHER("Fletcher", "Extractor", "fletcher.gif"),
    OIL_SMELTER("Oil Smelter", "Extractor", "oil-smelter.gif"),
    BAKERY("Bakery", "Extractor", "brewer.png"),
    BREWER("Brewer", "Extractor", "brewer.png"),
    MILL("Mill", "Extractor", "windmill.gif"),

    // Extractor:
    APPLE_ORCHARD("Apple Orchard", "Extractor", "apple-orchard.gif"),
    DAIRY_FARMER("Dairy Farmer", "Extractor", "dairy-farmer.gif"),
    HOPS_FARMER("Hops Farmer", "Extractor", "hops-farmer.gif"),
    HUNTERS_POST("Hunter Post", "Extractor", "hunters-post.gif"),
    WHEAT_FARMER("Wheat Farmer", "Extractor", "wheat-farmer.gif"),
    IRON_MINE("Iron Mine", "Extractor", "iron-mine.gif"),
    PITCH_RIG("Pitch Rig", "Extractor", "pitch-rig.gif"),
    QUARRY("Quarry", "Extractor", "quarry.gif"),
    WOODCUTTER("Woodcutter", "Extractor", "wood-cutter.png"),
    STABLE("Stable", "Extractor", "stable.png"),

    // Church:
    CHURCH("Church", "Church", "church.png"),
    CATHEDRAL("Cathedral", "Church", "cathedral.png"),

    // Other:
    INN("Inn", "Inn", "inn.png"),
    MARKET("Market", "Shop", "market.png"),
    HOVEL("Hovel", "House", "hovel.gif"),
    TUNNEL_ENTRANCE("Tunnel Entrance", "Building", "killing-pit.png"),
    OX_TETHER("Ox Tether", "Building", "ox-tether.gif"),
    SHORT_WALL("Short Wall", "Building", "wall.png"),
    TALL_WALL("Tall Wall", "Building", "wall.png"),
    STAIR("Stair", "Building", "wall.png"),
    CASTLE("Castle", "Castle", "castle.png"),


    ;
    private final String name;
    public final String kind;
    private final String imagePath;

    BuildingName(String name, String kind, String imagePath) {
        this.name = name;
        this.kind = kind;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return "file:src/main/resources/images/buildings/" + imagePath;
    }
}
