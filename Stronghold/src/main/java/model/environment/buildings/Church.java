package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class Church extends UnitMakerBuilding {

    protected Church(int hp,
                     BuildingCategory category,
                     BuildingName name,
                     HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public Church(BuildingName name, Government government, Block block) {
        super(name, government, block);
        if (name.equals(BuildingName.CHURCH))
            government.addPopularity(2);
        if (name.equals(BuildingName.CATHEDRAL))
            government.addPopularity(4);
    }
}
