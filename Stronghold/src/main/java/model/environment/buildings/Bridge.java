package model.environment.buildings;

import model.environment.Direction;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class Bridge extends Building {

    Direction direction;

    boolean isOpen;

    protected Bridge(int size,
                     int hp,
                     BuildingCategory category,
                     BuildingName name,
                     HashMap<ResourcesName, Integer> price) {
        super(size, hp, category, name, price);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
