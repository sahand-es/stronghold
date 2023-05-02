package model.units;

import model.map.Block;
import model.resourecs.Armour;
import model.society.Government;
import model.units.enums.UnitNames;

import java.util.ArrayList;

public abstract class Person {
    private int hp;
    private int speed;
    private int defencePower;
    private ArrayList<Armour> armour;
    private UnitNames name;
    private Block block;
    private Government government;

    protected boolean canClimbLadder;
    protected boolean canDigMoat;
    private static final ArrayList<Person> allUnits = new ArrayList<>();


    protected Person(int hp, int speed, int defencePower, ArrayList<Armour> armour, UnitNames name, boolean canClimbLadder, boolean canDigMoat) {
        this.hp = hp;
        this.speed = speed;
        this.defencePower = defencePower;
        this.armour = armour;
        this.name = name;
        this.canClimbLadder = canClimbLadder;
        this.canDigMoat = canDigMoat;

        allUnits.add(this);
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public ArrayList<Armour> getArmour() {
        return armour;
    }

    public UnitNames getName() {
        return name;
    }

    public Block getBlock() {
        return block;
    }

    public void takeDamage(int damage) {

    }

    public void move(Block destination) {

    }

    private void die() {

    }

}
