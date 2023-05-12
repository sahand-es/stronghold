package model.units;

import model.map.Block;
import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.resourecs.Weapon;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitName;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Soldier extends Person {

    private final int damage;
    private final int attackRange;

    SoldierUnitState soldierUnitState;

    Soldier(int hp, int speed, int defencePower,
            int damage, int attackRange,
            UnitName name,
            boolean canClimbLadder, boolean canDigMoat,
            HashMap<ResourcesName, Integer> price) {

        super(hp, speed, defencePower, name, price, canClimbLadder, canDigMoat);

        this.damage = damage;
        this.attackRange = attackRange;
    }

    public Soldier(UnitName name) {
        super(name);
        Soldier soldierToClone = (Soldier) getPersonByUnitName(name);

        this.soldierUnitState = SoldierUnitState.STANDING;
        this.attackRange = soldierToClone.attackRange;
        this.damage = soldierToClone.damage;
    }

    public SoldierUnitState getSoldierUnitState() {
        return soldierUnitState;
    }

    public void setSoldierUnitState(SoldierUnitState soldierUnitState) {
        this.soldierUnitState = soldierUnitState;
    }

    public void attackEnemy(Person enemyToAttack) {

    }

    @Override
    public String toString() {
        return this.name + "{" +
                "hp=" + hp +
                ", speed=" + speed +
                ", defencePower=" + defencePower +
                ", attackRange=" + attackRange +
                ", damage=" + damage +
                ", price=" + price +
                "}\n";
    }
}



