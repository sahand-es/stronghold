package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class HouseBuilding extends Building{

    private int peopleCapacity;


    public HouseBuilding(int size, int hp,
                         BuildingCategory category,
                         HashMap<ResourcesName, Integer> price,
                         int peopleCapacity) {

        super(size, hp, category, price);
        this.peopleCapacity = peopleCapacity;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }
}
