package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.HashMap;

public class HouseBuilding extends Building {

    private int peopleCapacity;


    protected HouseBuilding(int hp,
                            BuildingCategory category,
                            BuildingName name,
                            HashMap<ResourcesName, Integer> price,
                            int peopleCapacity) {

        super(hp, category, name, price);
        this.peopleCapacity = peopleCapacity;
    }


    public HouseBuilding(BuildingName name, Government government, Block block) {
        super(name, government, block);
        HouseBuilding buildingToClone = (HouseBuilding) getBuildingByBuildingName(name);
        this.peopleCapacity = buildingToClone.peopleCapacity;
        government.addPopulationCapacity(this.peopleCapacity);
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    @Override
    public void die(){
        government.subtractPopulationCapacity(this.peopleCapacity);
        government.removeBuilding(this);
        this.getBlock().setEnvironment(null);
    }
}
