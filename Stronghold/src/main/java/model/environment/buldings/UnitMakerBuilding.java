package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;
import model.units.Person;
import model.units.Soldier;

import java.util.ArrayList;

public class UnitMakerBuilding extends Building{

    ArrayList<Soldier> units;

    public UnitMakerBuilding(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, ArrayList<Soldier> units) {
        super(name, size, category, hp, owner, price);
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
