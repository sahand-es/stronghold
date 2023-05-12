package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resourecs.ResourceHolder;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageBuilding extends Building {

    int capacity;
    ArrayList<ResourceHolder> storage;

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
    }

    public ArrayList<ResourceHolder> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<ResourceHolder> storage) {
        this.storage = storage;
    }

    public int getCapacity() {
        return capacity;
    }
}
