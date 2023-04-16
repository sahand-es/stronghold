package model.units;

import model.map.Block;
import model.resourecs.Armour;
import model.resourecs.Weapon;
import model.units.enums.SoldierUnitState;

public class Soldier extends Person {

    Weapon weapon;

    SoldierUnitState soldierUnitState;

    public Soldier(int hp, int speed, int defencePower, Armour armour, String name, Block block, Weapon weapon) {
        super(hp, speed, defencePower, armour, name, block);
        this.weapon = weapon;
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



