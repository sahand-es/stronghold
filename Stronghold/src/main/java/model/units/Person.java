package model.units;

import model.map.Block;
import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.enums.UnitNames;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Person {
    protected int hp;
    protected int speed;
    protected int defencePower;
    protected ArrayList<Armour> armour;
    protected UnitNames name;

    protected final HashMap<ResourcesName, Integer> price;
    protected Block block;
    protected Government government;

    protected boolean canClimbLadder;
    protected boolean canDigMoat;
    protected static final ArrayList<Person> allUnits = new ArrayList<>();


    protected Person(int hp, int speed, int defencePower,
                     ArrayList<Armour> armour, UnitNames name,
                     HashMap<ResourcesName, Integer> price,
                     boolean canClimbLadder, boolean canDigMoat) {
        this.hp = hp;
        this.speed = speed;
        this.defencePower = defencePower;
        this.armour = armour;
        this.name = name;
        this.price = price;
        this.canClimbLadder = canClimbLadder;
        this.canDigMoat = canDigMoat;

        allUnits.add(this);
    }

    public Person(String name) {
        Person personToClone = getPersonByName(name);

        this.hp = personToClone.hp;
        this.speed = personToClone.speed;
        this.defencePower = personToClone.defencePower;
        this.armour = personToClone.armour;
        this.name = personToClone.name;
        this.price = personToClone.price;
        this.canClimbLadder = personToClone.canClimbLadder;
        this.canDigMoat = personToClone.canDigMoat;
    }

    public Person(UnitNames name) {
        Person personToClone = getPersonByUnitName(name);

        this.hp = personToClone.hp;
        this.speed = personToClone.speed;
        this.defencePower = personToClone.defencePower;
        this.armour = personToClone.armour;
        this.name = personToClone.name;
        this.price = personToClone.price;
        this.canClimbLadder = personToClone.canClimbLadder;
        this.canDigMoat = personToClone.canDigMoat;
    }

    protected Person getPersonByName(String name) {
        for (Person unit : allUnits) {
            if (unit.getName().equals(UnitNames.getUnitByName(name)))
                return unit;
        }
        return null;
    }

    protected Person getPersonByUnitName(UnitNames name) {
        for (Person unit : allUnits) {
            if (unit.getName().equals(name))
                return unit;
        }
        return null;
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
