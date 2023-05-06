package model.environment.buldings;

import model.environment.Direction;
import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class Gate extends HouseBuilding{

    Direction direction;

    protected Gate(int size,
                   int hp,
                   BuildingCategory category,
                   HashMap<ResourcesName, Integer> price,
                   int peopleCapacity) {

        super(size, hp, category, price, peopleCapacity);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
