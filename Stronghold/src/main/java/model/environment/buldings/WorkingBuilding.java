package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;
import model.units.Person;

import java.util.ArrayList;

public abstract class WorkingBuilding extends Building {

    ArrayList<Person> workers;

    public WorkingBuilding(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, ArrayList<Person> workers) {
        super(name,size,category, hp, owner, price);
        this.workers = workers;
    }


}
