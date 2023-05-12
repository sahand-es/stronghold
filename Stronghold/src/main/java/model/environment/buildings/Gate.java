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
                   BuildingName name,
                   HashMap<ResourcesName, Integer> price,
                   int peopleCapacity) {

        super(size, hp, category, name, price, peopleCapacity);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}