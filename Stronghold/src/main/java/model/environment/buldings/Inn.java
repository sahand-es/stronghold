package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;
import model.units.Person;

import java.util.ArrayList;

public class Inn extends WorkingBuilding {
    int popularityRate;

    int whineUsageRate;

    public Inn(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, ArrayList<Person> workers, int popularityRate, int whineUsageRate) {
        super(name, size, category, hp, owner, price, workers);
        this.popularityRate = popularityRate;
        this.whineUsageRate = whineUsageRate;
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
