package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;

import java.util.ArrayList;

public class StorageBuilding extends Building{

    ArrayList<Resource> storage;

    public StorageBuilding(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, ArrayList<Resource> storage) {
        super(name,size,category,hp, owner, price);
        this.storage = storage;
    }

    public ArrayList<Resource> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<Resource> storage) {
        this.storage = storage;
    }
}
