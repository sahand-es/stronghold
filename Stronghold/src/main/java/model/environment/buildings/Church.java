package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;
import model.units.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class Church extends Building {
    private ArrayList<Soldier> monks;

    protected Church(int size,
                     int hp,
                     BuildingCategory category,
                     BuildingName name,
                     HashMap<ResourcesName, Integer> price) {
        super(size, hp, category, name, price);
    }

    public void makeMonk() {

    }
}
