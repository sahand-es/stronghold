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
    private Armour armour;
    private UnitNames name;
    private Block block;
    private Government government;
    private static ArrayList<Person> allUnits;


    public Person(int hp, int speed, int defencePower, Armour armour, UnitNames name, Block block) {
        this.hp = hp;
        this.speed = speed;
        this.defencePower = defencePower;
        this.armour = armour;
        this.name = name;
        this.block = block;
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

    public Armour getArmour() {
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
