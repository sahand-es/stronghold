package model.environment.buildings;

import model.environment.Direction;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;

import java.util.HashMap;

public class Gate extends HouseBuilding{

    Direction direction;

    protected Gate(int size,
                   int hp,
                   BuildingCategory category,
                   HashMap<ResourcesName, Integer> price,
                   BuildingName name,
                   int peopleCapacity) {

        super(size, hp, category, price, name, peopleCapacity);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
