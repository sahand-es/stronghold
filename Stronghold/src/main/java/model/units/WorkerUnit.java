package model.units;

import model.map.Block;
import model.resource.ResourcesName;
import model.society.Government;
import model.units.enums.UnitName;

import java.util.HashMap;

public class WorkerUnit extends Person {



    protected WorkerUnit(int hp, int speed, int defencePower, UnitName name, HashMap<ResourcesName, Integer> price, boolean canClimbLadder, boolean canDigMoat) {
        super(hp, speed, defencePower, name, price, canClimbLadder, canDigMoat);
    }

    public WorkerUnit(UnitName name, Block block, Government government) {
        super(name, block, government);
    }

    public WorkerUnit(UnitName name) {
        super(name);
    }

    @Override
    public String toString() {
        return this.name + "{" +
                "hp=" + hp +
                ", speed=" + speed +
                ", defencePower=" + defencePower +
                ", price=" + price +
                ", canClimbLadder=" + canClimbLadder +
                ", canDigMoat=" + canDigMoat +
                "}\n";
    }
}
