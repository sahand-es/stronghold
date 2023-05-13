package model.environment.buildings;

import model.map.Block;
import model.map.Direction;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Person;
import model.units.enums.UnitName;

import java.util.HashMap;

public class Gate extends HouseBuilding{

    Direction direction;

    protected Gate(int hp,
                   BuildingCategory category,
                   BuildingName name,
                   HashMap<ResourcesName, Integer> price,
                   int peopleCapacity) {

        super(hp, category, name, price, peopleCapacity);
    }

    public Gate(BuildingName name, Government government, Block block) {
        super(name, government, block);
        direction = Direction.UP;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean canPassBuilding(Person person){
        if(person.getName().equals(UnitName.ASSASSIN))
            return true;

        if (person.getGovernment().equals(this.government))
            return true;

        return false;
    }
}
