package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageBuilding extends Building {

    int capacity;
    ArrayList<Resource> storage;

    protected StorageBuilding(int size,
                              int hp,
                              BuildingCategory category,
                              BuildingName name,
                              HashMap<ResourcesName, Integer> price,
                              int capacity) {

        super(size, hp, category, name, price);
        this.capacity = capacity;
    }

    public ArrayList<Resource> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<Resource> storage) {
        this.storage = storage;
    }

    public int getCapacity() {
        return capacity;
    }
}
