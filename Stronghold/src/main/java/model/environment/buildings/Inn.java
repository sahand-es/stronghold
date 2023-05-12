package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.HashMap;

public class Inn extends Building {
    int popularityRate;

    int whineUsageRate;

    protected Inn(int hp,
                  BuildingCategory category,
                  BuildingName name,
                  HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public Inn(BuildingName name, Government government, Block block) {
        super(name, government, block);
    }

    public int getPopularityRate() {
        return popularityRate;
    }

    public void setPopularityRate(int popularityRate) {
        this.popularityRate = popularityRate;
    }

    public int getWhineUsageRate() {
        return whineUsageRate;
    }

    public void setWhineUsageRate(int whineUsageRate) {
        this.whineUsageRate = whineUsageRate;
    }
}
