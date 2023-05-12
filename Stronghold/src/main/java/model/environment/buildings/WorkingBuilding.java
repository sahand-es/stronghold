package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;
import model.units.Person;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class WorkingBuilding extends Building {

    ArrayList<Person> workers;

    protected WorkingBuilding(int hp,
                              BuildingCategory category,
                              BuildingName name,
                              HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }
}
