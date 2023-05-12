package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class Church extends Building {
    private ArrayList<Soldier> monks;

    protected Church(int hp,
                     BuildingCategory category,
                     BuildingName name,
                     HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public Church(BuildingName name, Government government, Block block) {
        super(name, government, block);
        monks = new ArrayList<>();
        government.addPopularity(2);
    }

    public void makeMonk() {

    }
}