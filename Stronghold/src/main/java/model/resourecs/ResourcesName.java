package model.resourecs;

public enum ResourcesName
{
//  Initial materials:
    GOLD("Gold"),
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
    STAFF("Staff"),
    TORCH("Torch"),
    SLING("Sling"),
    SCIMITAR("Scimitar"),
    GREEK_FIRE("Greek Fire"),

//  Armors:
    LEATHER("Leather"),
    METAL("Metal"),
    HORSE("Horse"),

//  Others:
    COW("Cow");


    private final String name;

    ResourcesName(String name) {
        this.name = name;
    }

    public static ResourcesName getResourceByName(String resourceName)
    {
        for (ResourcesName resource : ResourcesName.values()) {
            if (resource.name.equalsIgnoreCase(resourceName.trim()))
                return resource;
        }
        return null;
    }
}
