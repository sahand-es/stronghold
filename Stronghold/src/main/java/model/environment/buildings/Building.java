package model.environment.buildings;

import model.environment.Environment;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class Building extends Environment {

    Government owner;
    int hp;
    BuildingCategory category;
    BuildingName name;
    HashMap<ResourcesName, Integer> price;

    ArrayList<Building> allBuildings = new ArrayList<>();

    protected Building(int size,
                       int hp,
                       BuildingCategory category,
                       BuildingName name,
                       HashMap<ResourcesName, Integer> price) {
        super(size);
        this.hp = hp;
        this.category = category;
        this.name = name;
        this.price = price;

        allBuildings.add(this);
    }

    public Government getOwner() {
        return owner;
    }

    public void setOwner(Government owner) {
        this.owner = owner;
    }

    public HashMap<ResourcesName, Integer> getPrice() {
        return price;
    }
}
