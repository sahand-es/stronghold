package model.resource;

import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Armour extends ResourceHolder {
    int speedBoost;
    int defenceBoost;

    protected Armour(String name, HashMap<ResourcesName, Integer> price, int howManyFor1Price, int speedBoost, int defenceBoost) {
        super(name, price, howManyFor1Price);
        this.speedBoost = speedBoost;
        this.defenceBoost = defenceBoost;
    }

    @Override
    public String toString() {
        return "Armour{" +
                "kind=" + kind +
                ", count=" + count +
                ", price=" + getPrice() +
                ", howManyFor1Price=" + howManyFor1Price +
                " speedBoost=" + speedBoost +
                ", defenceBoost=" + defenceBoost +
                "}\n";
    }

    public Armour(String name, int count) {
        super(name, count);

        Armour armourToClone = (Armour) getResourceByName(name);
        this.speedBoost = armourToClone.speedBoost;
        this.defenceBoost = armourToClone.defenceBoost;
    }

    public Armour(String name) {
        super(name);

        Armour armourToClone = (Armour) getResourceByName(name);
        this.speedBoost = armourToClone.speedBoost;
        this.defenceBoost = armourToClone.defenceBoost;
    }

    public Armour(ResourcesName name, int count) {
        super(name, count);

        Armour armourToClone = (Armour) getResourceByResourcesName(name);
        this.speedBoost = armourToClone.speedBoost;
        this.defenceBoost = armourToClone.defenceBoost;
    }

    public Armour(ResourcesName name) {
        super(name);

        Armour armourToClone = (Armour) getResourceByResourcesName(name);
        this.speedBoost = armourToClone.speedBoost;
        this.defenceBoost = armourToClone.defenceBoost;
    }

    public int getSpeedBoost() {
        return speedBoost;
    }

    public int getDefenceBoost() {
        return defenceBoost;
    }
}
