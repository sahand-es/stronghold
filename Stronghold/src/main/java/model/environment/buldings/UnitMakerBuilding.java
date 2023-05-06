package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Person;
import model.units.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class UnitMakerBuilding extends Building{

    ArrayList<Soldier> units;

    protected UnitMakerBuilding(int size, int hp, BuildingCategory category, HashMap<ResourcesName, Integer> price, ArrayList<Soldier> units) {
        super(size, hp, category, price);
        this.units = units;
    }

    public ArrayList<Soldier> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Soldier> units) {
        this.units = units;
    }

    public void makeUnit(Soldier soldier){

    }
}
