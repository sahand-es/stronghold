package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resource.ResourcesName;
import model.society.Government;

import java.util.HashMap;

public class StorageBuilding extends Building {

    int capacity;

    protected StorageBuilding(int hp,
                              BuildingCategory category,
                              BuildingName name,
                              HashMap<ResourcesName, Integer> price,
                              int capacity) {

        super(hp, category, name, price);
        this.capacity = capacity;
    }

    public StorageBuilding(BuildingName name, Government government, Block block) {
        super(name, government, block);
        StorageBuilding buildingToClone = (StorageBuilding) getBuildingByBuildingName(name);
        this.capacity = buildingToClone.capacity;
        government.addCapacity(name,this.capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public void die(){
        super.die();
        government.addCapacity(name,-1 * this.capacity);
    }


}
