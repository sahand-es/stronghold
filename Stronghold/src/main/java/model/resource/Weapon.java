package model.resource;

import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Weapon extends ResourceHolder {

    private final int damage;
    private final int attackRange;

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV(DataManager.WEAPONS_PATH);
        String[] attributeNames = resourceCsv.get(0);

        start:
        for (int i = 1; i < resourceCsv.size(); i++) {
            String[] attributes = resourceCsv.get(i);
            int damage = 0, attackRange = 0;
            String name = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();
            int howMany = 0;
            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Type": {
                        if (!attributes[j].equalsIgnoreCase("Weapon"))
                            continue start;
                        break;
                    }
                    case "Name": {
                        name = attributes[j];
                        break;
                    }
                    case "Damage": {
                        damage = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Attack Range": {
                        attackRange = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Price Kind": {
                        if (!attributes[j].equals("null")) {
                            price.put(ResourcesName.getResourceByName(attributes[j]), Integer.parseInt(attributes[j + 1]));
                        }
                        break;
                    }
                    case "Count": {
                        howMany = Integer.parseInt(attributes[j]);
                        break;
                    }
                }
            }

            new Weapon(name, price, howMany, damage, attackRange);
        }


    }



    private Weapon(String name, HashMap<ResourcesName, Integer> price, int howManyFor1Price, int damage, int attackRange) {
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
