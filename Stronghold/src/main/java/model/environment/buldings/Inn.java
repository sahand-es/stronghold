package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class Inn extends WorkingBuilding {
    int popularityRate;

    int whineUsageRate;

    protected Inn(int size, int hp, BuildingCategory category, HashMap<ResourcesName, Integer> price) {
        super(size, hp, category, price);
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
