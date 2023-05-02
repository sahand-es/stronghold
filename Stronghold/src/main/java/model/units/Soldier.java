package model.units;

import model.map.Block;
import model.resourecs.Armour;
import model.resourecs.Weapon;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitNames;

import java.util.ArrayList;

public class Soldier extends Person {

    Weapon weapon;

    SoldierUnitState soldierUnitState;
    public Soldier(int hp, int speed, int defencePower, ArrayList<Armour> armour, UnitNames name, boolean canClimbLadder, boolean canDigMoat, Weapon weapon, SoldierUnitState soldierUnitState) {
        super(hp, speed, defencePower, armour, name, canClimbLadder, canDigMoat);
        this.weapon = weapon;
        this.soldierUnitState = soldierUnitState;
    }

    public SoldierUnitState getSoldierUnitState()
    {
        return soldierUnitState;
    }

    public void setSoldierUnitState(SoldierUnitState soldierUnitState)
    {
        this.soldierUnitState = soldierUnitState;
    }
}



