package model.units.workerunits;

import model.map.Block;
import model.society.Government;
import model.units.WorkerUnit;
import model.units.enums.UnitName;

public class Tunneler extends WorkerUnit {

    public Tunneler() {
        super(UnitName.TUNNELER);
    }

    public Tunneler(UnitName name, Block block, Government government) {
        super(name, block, government);
    }

    public void makeTunnel(Block block) {

    }
}
