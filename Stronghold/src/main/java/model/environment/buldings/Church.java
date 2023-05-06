package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class Church extends Building{
    private ArrayList<Soldier> monks;

    protected Church(int size, int hp, BuildingCategory category, HashMap<ResourcesName, Integer> price) {
        super(size, hp, category, price);
    }

    public void makeMonk(){

    }
}
