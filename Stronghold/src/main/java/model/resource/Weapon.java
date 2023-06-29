package model.resource;

import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Weapon extends ResourceHolder {

    private final int damage;
    private final int attackRange;



    protected Weapon(String name, HashMap<ResourcesName, Integer> price, int howManyFor1Price, int damage, int attackRange) {
        super(name, price, howManyFor1Price);
        this.damage = damage;
        this.attackRange = attackRange;
    }

    public Weapon(String name, int count) {
        super(name, count);

        Weapon weaponToClone = (Weapon) getResourceByName(name);
        this.damage = weaponToClone.damage;
        this.attackRange = weaponToClone.attackRange;
    }

    public Weapon(String name) {
        super(name);

        Weapon weaponToClone = (Weapon) getResourceByName(name);
        this.damage = weaponToClone.damage;
        this.attackRange = weaponToClone.attackRange;
    }

    public Weapon(ResourcesName name, int count) {
        super(name, count);

        Weapon weaponToClone = (Weapon) getResourceByResourcesName(name);
        this.damage = weaponToClone.damage;
        this.attackRange = weaponToClone.attackRange;
    }

    public Weapon(ResourcesName name) {
        super(name);

        Weapon weaponToClone = (Weapon) getResourceByResourcesName(name);
        this.damage = weaponToClone.damage;
        this.attackRange = weaponToClone.attackRange;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "kind=" + kind +
                ", count=" + count +
                ", price=" + getPrice() +
                ", howManyFor1Price=" + howManyFor1Price +
                " damage=" + damage +
                ", attackRange=" + attackRange +
                "}\n";
    }
}
