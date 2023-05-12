package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;

import java.util.HashMap;

public class Traps extends Building{

    protected Traps(int size,
                    int hp,
                    BuildingCategory category,
                    BuildingName name,
                    HashMap<ResourcesName, Integer> price) {
        super(size, hp, category, name, price);
    }
}
