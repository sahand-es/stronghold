package model.environment.buildings;

import model.map.Direction;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;

import java.util.HashMap;

public class Bridge extends Building {

    Direction direction;

    boolean isOpen;

    protected Bridge(
                     int hp,
                     BuildingCategory category,
                     BuildingName name,
                     HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
