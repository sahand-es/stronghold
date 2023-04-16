package model.SiegeUtil;

import model.map.Block;

public abstract class SiegeUtil {
    String name;
    int hp;
    Block block;

    int defencePoint;

    int speed;

    public SiegeUtil(String name, int hp, Block block, int defencePoint,int speed) {
        this.name = name;
        this.hp = hp;
        this.block = block;
        this.defencePoint = defencePoint;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public int getDefencePoint() {
        return defencePoint;
    }

    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }
}
