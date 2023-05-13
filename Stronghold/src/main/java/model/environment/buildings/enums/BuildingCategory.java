package model.environment.buildings.enums;

import org.jetbrains.annotations.NotNull;

public enum BuildingCategory {
    CASTLE("Castle"),
    FARM("Farm"),
    FOOD_PROCESSING("Food Processing"),
    INDUSTRY("Industry"),
    TOWN("Town"),
    WEAPONS("Weapons"),
    ;
    private final String name;

    BuildingCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public static BuildingCategory getCategoryByName(String name) {
        for (BuildingCategory category : BuildingCategory.values()) {
            if (category.getName().equalsIgnoreCase(name))
                return category;
        }
        throw new RuntimeException();
    }


}
