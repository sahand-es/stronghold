package model.environment.buldings;

import model.environment.Environment;
import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.resourecs.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Building extends Environment
{
    Government owner;
    int hp;
    BuildingCategory category;
    HashMap<ResourcesName, Integer> price;

    ArrayList<Building> allBuildings = new ArrayList<>();

    protected Building(int size, int hp, BuildingCategory category, HashMap<ResourcesName, Integer> price) {
        super(size);
        this.hp = hp;
        this.category = category;
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
