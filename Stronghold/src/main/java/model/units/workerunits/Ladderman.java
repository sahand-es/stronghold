package model.units.workerunits;

import model.map.Block;
import model.society.Government;
import model.units.WorkerUnit;
import model.units.enums.UnitName;

public class Ladderman extends WorkerUnit {
    public Ladderman() {
        super(UnitName.LADDERMAN);
    }

    public Ladderman(UnitName name, Block block, Government government) {
        super(name, block, government);
    }

    public void putLadder() {

    }
}
