package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resource.ResourcesName;
import model.society.Government;
import model.units.Soldier;
import model.units.enums.UnitName;

import java.util.ArrayList;
import java.util.HashMap;

public class UnitMakerBuilding extends Building {

    ArrayList<UnitName> units;

    protected UnitMakerBuilding(int hp,
                                BuildingCategory category,
                                BuildingName name,
                                HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public UnitMakerBuilding(BuildingName name, Government government, Block block) {
        super(name, government, block);
        units = UnitName.getUnits(name);
    }

    public ArrayList<UnitName> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<UnitName> units) {
        this.units = units;
    }

    public void makeUnit(Soldier soldier) {

    }
}
