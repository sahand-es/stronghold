package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resource.ResourcesName;
import model.society.Government;

import java.util.HashMap;

public class DefensiveBuilding extends Building {
    private int fireRange;
    private int defenceRange;

    private int damage;

    protected DefensiveBuilding(int hp, BuildingCategory category,
                                HashMap<ResourcesName, Integer> price,
                                BuildingName name,
                                int fireRange, int defenceRange, int damage) {
        super(hp, category, name, price);
        this.fireRange = fireRange;
        this.defenceRange = defenceRange;
        this.damage = damage;
    }

    public DefensiveBuilding(BuildingName name, Government government, Block block) {
        super(name, government, block);
        DefensiveBuilding defensiveBuilding = (DefensiveBuilding) getBuildingByBuildingName(name);
        this.fireRange = defensiveBuilding.getFireRange();
        this.defenceRange = defensiveBuilding.getDefenceRange();
        this.damage = defensiveBuilding.getDamage();
    }

    public int getFireRange() {
        return fireRange;
    }

    public void setFireRange(int fireRange) {
        this.fireRange = fireRange;
    }

    public int getDefenceRange() {
        return defenceRange;
    }

    public void setDefenceRange(int defenceRange) {
        this.defenceRange = defenceRange;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}


