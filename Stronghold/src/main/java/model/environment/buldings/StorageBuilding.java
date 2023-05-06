package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageBuilding extends Building{

    int capacity;
    ArrayList<Resource> storage;

    protected StorageBuilding(int size,
                           int hp,
                           BuildingCategory category,
                           HashMap<ResourcesName, Integer> price,
                           int capacity) {

        super(size, hp, category, price);
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
