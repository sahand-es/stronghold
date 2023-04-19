package model.siegeutil;

import model.map.Block;

public class AttackerSiegeUnit extends SiegeUtil {

    int damagePoint;
    int range;

    public AttackerSiegeUnit(String name, int hp, Block block, int defencePoint,int speed, int damagePoint, int range) {
        super(name, hp, block, defencePoint,speed);
        this.damagePoint = damagePoint;
        this.range = range;
    }

    public int getDamagePoint() {
        return damagePoint;
    }

    public void setDamagePoint(int damagePoint) {
        this.damagePoint = damagePoint;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
