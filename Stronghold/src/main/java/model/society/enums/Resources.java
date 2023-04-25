package model.society.enums;

public enum Resources
{
//  Initial materials:
    WHEAT("Wheat"),
    FLOUR("Flour"),
    HOPS("Hops"),
    ALE("Ale"),
    STONE("Stone"),
    IRON("Iron"),
    WOOD("Wood"),
    PITCH("Pitch"),

//  Foods:
    MEAT("Meat"),
    APPLE("Apple"),
    CHEESE("Cheese"),
    BREAD("Bread"),

//  Weapons:
    BOW("Bow"),
    CROSSBOW("Crossbow"),
    SPEAR("Spear"),
    PIKE("Pike"),
    MACE("Mace"),
    SWORD("Sword"),

//  Armors:
    LEATHER("Leather"),
    METAL("Metal"),
    HORSE("Horse"),

//  Others:
    COW("Cow");


    private final String name;

    Resources(String name) {
        this.name = name;
    }

    public static Resources getResourceByName(String resourceName)
    {
        for (Resources resource : Resources.values()) {
            if (resource.name.equalsIgnoreCase(resourceName.trim()))
                return resource;
        }
        return null;
    }
}
