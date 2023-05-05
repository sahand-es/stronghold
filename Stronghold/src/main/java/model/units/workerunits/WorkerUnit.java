package model.units.workerunits;

import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.resourecs.Weapon;
import model.units.Person;
import model.units.Soldier;
import model.units.enums.UnitNames;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkerUnit extends Person {

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV(DataManager.UNITS_PATH);
        String[] attributeNames = resourceCsv.get(0);

        start:
        for (int i = 1; i < resourceCsv.size(); i++) {

            String[] attributes = resourceCsv.get(i);
            int hp = 0, speed = 0, defencePower = 0;
            ArrayList<Armour> armours = new ArrayList<>();
            UnitNames name = null;
            boolean canClimbLadder = false, canDigMoat = false;
            Weapon weaponToAdd = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();

            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Kind": {
                        if (!attributes[j].equalsIgnoreCase("Worker"))
                            continue start;
                    }
                    case "Name": {
                        name = UnitNames.getUnitByName(attributes[j]);
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

            new WorkerUnit(hp, speed, defencePower, armours, name, price, canClimbLadder, canDigMoat);
        }
    }

    private WorkerUnit(int hp, int speed, int defencePower, ArrayList<Armour> armour, UnitNames name, HashMap<ResourcesName, Integer> price, boolean canClimbLadder, boolean canDigMoat) {
        super(hp, speed, defencePower, armour, name, price, canClimbLadder, canDigMoat);
    }


    public WorkerUnit(UnitNames name) {
        super(name);
    }

    @Override
    public String toString() {
        return this.name + "{" +
                "hp=" + hp +
                ", speed=" + speed +
                ", defencePower=" + defencePower +
                ", armour=" + armour +
                ", price=" + price +
                ", canClimbLadder=" + canClimbLadder +
                ", canDigMoat=" + canDigMoat +
                "}\n";
    }

    public static void main(String[] args) {
        Soldier s = new Soldier(UnitNames.ARCHER);
        System.out.println(allUnits);
    }
}
