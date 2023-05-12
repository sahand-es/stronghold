package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;
import model.units.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class UnitMakerBuilding extends Building {

    ArrayList<Soldier> units;

    protected UnitMakerBuilding(int size,
                                int hp,
                                BuildingCategory category,
                                BuildingName name,
                                HashMap<ResourcesName, Integer> price) {
        super(size, hp, category, name, price);
    }

    public ArrayList<Soldier> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Soldier> units) {
        this.units = units;
    }

    public void makeUnit(Soldier soldier) {

    }
}
