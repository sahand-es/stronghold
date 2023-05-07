package model.units;

import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.resourecs.Weapon;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitName;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Soldier extends Person {

    Weapon weapon;

    SoldierUnitState soldierUnitState;

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV(DataManager.UNITS_PATH);
        String[] attributeNames = resourceCsv.get(0);

        start:
        for (int i = 1; i < resourceCsv.size(); i++) {

            String[] attributes = resourceCsv.get(i);
            int hp = 0, speed = 0, defencePower = 0;
            ArrayList<Armour> armours = new ArrayList<>();
            UnitName name = null;
            boolean canClimbLadder = false, canDigMoat = false;
            Weapon weaponToAdd = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();

            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Kind": {
                        if (!attributes[j].equalsIgnoreCase("Soldier"))
                            continue start;
                        break;
                    }
                    case "Name": {
                        name = UnitName.getUnitByName(attributes[j]);
                        break;
                    }
                    case "Hp": {
                        hp = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Speed": {
                        speed = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Defence Power": {
                        defencePower = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Armour": {
                        if (!attributes[j].equals("null")) {
                            String[] armourNames = attributes[j].split("~");

                            for (String armourName : armourNames) {
                                armours.add(new Armour(armourName));
                            }
                        }
                        break;
                    }
                    case "Weapon": {
                        weaponToAdd = new Weapon(attributes[j]);
                        break;
                    }
                    case "Price kind": {
                        String[] priceKinds = attributes[j].split("~");
                        String[] priceCounts = attributes[j + 1].split("~");

                        for (int i1 = 0; i1 < priceKinds.length && i1 < priceCounts.length; i1++) {
                            price.put(
                                    ResourcesName.getResourceByName(priceKinds[i1]),
                                    Integer.parseInt(priceCounts[i1])
                            );
                        }
                        break;
                    }
                    case "Climb Ladder": {
                        switch (attributes[j]) {
                            case "Yes": {
                                canClimbLadder = true;
                                break;
                            }
                            case "No": {
                                canClimbLadder = false;
                                break;
                            }
                        }
                    }
                    case "Dig Moat": {
                        switch (attributes[j]) {
                            case "Yes": {
                                canDigMoat = true;
                                break;
                            }
                            case "No": {
                                canDigMoat = false;
                                break;
                            }
                        }
                    }
                }
            }

            new Soldier(hp, speed, defencePower, armours, name, canClimbLadder, canDigMoat, price, weaponToAdd);
        }
    }

    private Soldier(int hp, int speed, int defencePower,
                    ArrayList<Armour> armour, UnitName name,
                    boolean canClimbLadder, boolean canDigMoat,
                    HashMap<ResourcesName, Integer> price, Weapon weapon) {
        super(hp, speed, defencePower, armour, name, price, canClimbLadder, canDigMoat);
        this.weapon = weapon;
        this.soldierUnitState = SoldierUnitState.STANDING;
    }

    public Soldier(UnitName name) {
        super(name);
        Soldier soldierToClone = (Soldier) getPersonByUnitName(name);

        this.weapon = soldierToClone.weapon;
        this.soldierUnitState = SoldierUnitState.STANDING;
    }

    public Soldier(String name) {
        super(name);
        Soldier soldierToClone = (Soldier) getPersonByName(name);

        this.weapon = soldierToClone.weapon;
        this.soldierUnitState = SoldierUnitState.STANDING;
    }

    public SoldierUnitState getSoldierUnitState() {
        return soldierUnitState;
    }

    public void setSoldierUnitState(SoldierUnitState soldierUnitState) {
        this.soldierUnitState = soldierUnitState;
    }

    @Override
    public String toString() {
        return this.name + "{" +
                "weapon=" + weapon.getKind() +
                ", hp=" + hp +
                ", speed=" + speed +
                ", defencePower=" + defencePower +
                ", armour=" + armour +
                ", price=" + price +
                "}\n";
    }

}



