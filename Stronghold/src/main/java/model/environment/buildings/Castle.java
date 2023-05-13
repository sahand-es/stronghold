package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.HashMap;

public class Castle extends Building {

    public Castle(int hp, BuildingCategory category, BuildingName name, HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public Castle(BuildingName name, Government government, Block block) {
        super(name, government, block);
    }


    @Override
    public void die(){
        //TODO;
    }
}
