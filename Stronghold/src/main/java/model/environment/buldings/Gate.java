package model.environment.buldings;

import model.map.Direction;
import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;

import java.util.ArrayList;

public class Gate extends HouseBuilding{

    Direction direction;

    public Gate(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, int peopleCapacity, Direction direction) {
        super(name, size, category, hp, owner, price, peopleCapacity);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
