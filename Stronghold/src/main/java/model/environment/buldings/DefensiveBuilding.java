package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class DefensiveBuilding extends Building {
    private int fireRange;
    private int defenceRange;

    private int damage;

    protected DefensiveBuilding(int size, int hp, BuildingCategory category,
                             HashMap<ResourcesName, Integer> price,
                             int fireRange, int defenceRange, int damage) {
        super(size, hp, category, price);
        this.fireRange = fireRange;
        this.defenceRange = defenceRange;
        this.damage = damage;
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


