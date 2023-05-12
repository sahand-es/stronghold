package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;

import java.util.HashMap;

public class HouseBuilding extends Building {

    private int peopleCapacity;


    protected HouseBuilding(int size, int hp,
                            BuildingCategory category,
                            BuildingName name,
                            HashMap<ResourcesName, Integer> price,
                            int peopleCapacity) {

        super(size, hp, category, name, price);
        this.peopleCapacity = peopleCapacity;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }
}
