package model.units.workerunits;

import model.map.Block;
import model.map.Direction;
import model.map.Texture;
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
        int x, y;
        for (int i = 1; i <= 3; i++) {
            x = block.getX() + direction.deltaX * i;
            y = block.getY() + direction.deltaY * i;
            if (block.getMap().isValidXY(x, y)) {
                block.getMap().getBlockByXY(x, y).setTexture(Texture.OIL);
            }
        }
    }


    public void build() {

    }
}
