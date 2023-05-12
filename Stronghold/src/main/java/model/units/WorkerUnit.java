package model.units;

import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.resourecs.Weapon;
import model.units.Person;
import model.units.Soldier;
import model.units.enums.UnitName;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkerUnit extends Person {



    protected WorkerUnit(int hp, int speed, int defencePower, UnitName name, HashMap<ResourcesName, Integer> price, boolean canClimbLadder, boolean canDigMoat) {
        super(hp, speed, defencePower, name, price, canClimbLadder, canDigMoat);
    }


    public WorkerUnit(UnitName name) {
        super(name);
    }

    @Override
    public String toString() {
        return this.name + "{" +
                "hp=" + hp +
                ", speed=" + speed +
                ", defencePower=" + defencePower +
                ", price=" + price +
                ", canClimbLadder=" + canClimbLadder +
                ", canDigMoat=" + canDigMoat +
                "}\n";
    }
}
