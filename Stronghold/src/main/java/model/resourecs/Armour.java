package model.resourecs;

import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Armour extends ResourceHolder {
    int speedBoost;
    int defenceBoost;

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV(DataManager.WEAPONS_PATH);
        String[] attributeNames = resourceCsv.get(0);

        start:
        for (int i = 1; i < resourceCsv.size(); i++) {
            String[] attributes = resourceCsv.get(i);
            int speedBoost = 0, defenceBoost = 0;
            String name = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();
            int howMany = 0;
            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Type": {
                        if (!attributes[j].equalsIgnoreCase("Armour"))
                            continue start;
                        break;
                    }
                    case "Name": {
                        name = attributes[j];
                        break;
                    }
                    case "Speed Boost": {
                        speedBoost = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Defence Boost": {
                        defenceBoost = Integer.parseInt(attributes[j]);
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

                new Armour(name, price, howMany, speedBoost, defenceBoost);
        }
    }

    private Armour(String name, HashMap<ResourcesName, Integer> price, int howManyFor1Price, int speedBoost, int defenceBoost) {
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
}
