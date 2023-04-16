package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;
import model.units.Soldier;

import java.util.ArrayList;

public class Church extends Building{
    private ArrayList<Soldier> monks;

    public Church(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, ArrayList<Soldier> monks) {
        super(name, size, category, hp, owner, price);
        this.monks = monks;
    }

    public void makeMonk(){

    }
}
