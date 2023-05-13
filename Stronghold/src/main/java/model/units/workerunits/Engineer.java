package model.units.workerunits;

import model.map.Block;
import model.map.Direction;
import model.society.Government;
import model.units.WorkerUnit;
import model.units.enums.UnitName;

public class Engineer extends WorkerUnit {
    public Engineer() {
        super(UnitName.ENGINEER);
    }

    public Engineer(UnitName name, Block block, Government government) {
        super(name, block, government);
    }

    public void pourOil(Direction direction) {

    }

    public void build() {

    }
}
