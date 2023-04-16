package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;

import java.util.ArrayList;

public class HouseBuilding extends Building{

    private int peopleCapacity;

    public HouseBuilding(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, int peopleCapacity) {
        super(name, size, category, hp, owner, price);
        this.peopleCapacity = peopleCapacity;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }
}
