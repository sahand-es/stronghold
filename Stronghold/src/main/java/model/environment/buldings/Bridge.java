package model.environment.buldings;

import model.environment.Direction;
import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;

import java.util.ArrayList;

public class Bridge extends Building{

    Direction direction;

    boolean isOpen;

    public Bridge(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, Direction direction, boolean isOpen) {
        super(name, size, category, hp, owner, price);
        this.direction = direction;
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
