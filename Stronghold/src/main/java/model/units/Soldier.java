package model.units;

import model.Game;
import model.map.Block;
import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.resourecs.Weapon;
import model.society.Government;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitName;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Soldier extends Person {

    private final int damage;
    private final int attackRange;
    Queue<Person> attackQueue = new LinkedList<>();

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

    public Queue<Person> getAttackQueue() {
        return attackQueue;
    }

    public Soldier(UnitName name) {
        super(name);
        Soldier soldierToClone = (Soldier) getPersonByUnitName(name);

        this.soldierUnitState = SoldierUnitState.STANDING;
        this.attackRange = soldierToClone.attackRange;
        this.damage = soldierToClone.damage;
    }

    public Soldier(UnitName name, Block block, Government government) {
        super(name, block, government);
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

    public boolean setAttackQueue(Person enemyToAttack) {
        attackQueue = new LinkedList<>();
        attackQueue.add(enemyToAttack);
        Block enemyBlock = enemyToAttack.getBlock();
        Block closestBlock = enemyBlock.findClosestBlock(attackRange, block);

        if (!canGoThere(closestBlock))
            return false;

        findPath(closestBlock);
        return true;
    }

    public Person getOpponnet() {
        return attackQueue.peek();
    }

    public void freeAttackQueue() {
        attackQueue = new LinkedList<>();
    }
    public boolean isReadyToAttack() {
       if (attackQueue.isEmpty())
           return false;
       setAttackQueue(attackQueue.peek());
       return moveQueue.isEmpty();
    }
    public int getDamage() {
        return damage + government.getFearRate();
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



