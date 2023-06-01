package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resource.ResourcesName;
import model.society.Government;

import java.util.HashMap;

public class Shop extends Building {
    protected Shop(int hp, BuildingCategory category,
                   BuildingName name,
                   HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public Shop(BuildingName name, Government government, Block block) {
        super(name, government, block);
    }
}
