package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;

import java.util.ArrayList;

public class Traps extends Building{

    public Traps(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price) {
        super(name,size,category, hp, owner, price);
    }


}
