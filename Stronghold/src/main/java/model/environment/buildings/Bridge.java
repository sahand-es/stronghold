package model.environment.buildings;

import model.map.Block;
import model.map.Direction;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resource.ResourcesName;
import model.society.Government;
import model.units.Person;
import model.units.enums.UnitName;

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

    public Bridge(BuildingName name, Government government, Block block) {
        super(name, government, block);
        this.direction = Direction.UP;
        this.isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


    @Override
    public boolean canPassBuilding(Person person){
        if(person.getUnitName().equals(UnitName.ASSASSIN))
            return true;

        return isOpen;
    }
}
