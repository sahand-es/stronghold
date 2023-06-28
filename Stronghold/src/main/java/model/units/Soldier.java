package model.units;

import model.environment.buildings.Building;
import model.map.Block;
import model.resource.ResourcesName;
import model.society.Government;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitName;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Soldier extends Person {

    private final int damage;
    private final int attackRange;
    Queue<Person> attackQueue = new LinkedList<>();
    Queue<Building> buildingAttackQueue = new LinkedList<>();

    SoldierUnitState soldierUnitState = SoldierUnitState.STANDING;

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

    public Queue<Building> getBuildingAttackQueue() {
        return buildingAttackQueue;
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
        Block enemyBlock = enemyToAttack.getBlock();
        Block closestBlock = enemyBlock.findClosestBlock(attackRange, block);

        if (!canGoThere(closestBlock))
            return false;

        attackQueue.add(enemyToAttack);

        findPath(closestBlock);
        return true;
    }

    public boolean setAttackQueueBuilding(Building buildingToAttack) {
        buildingAttackQueue = new LinkedList<>();
        Block enemyBlock = buildingToAttack.getBlock();
        Block closestBlock = enemyBlock.findClosestBlock(attackRange, block);

        if (!canGoThere(closestBlock))
            return false;

        buildingAttackQueue.add(buildingToAttack);

        findPath(closestBlock);
        freeAttackQueue();
        return true;
    }

    public Person getOpponent() {
        return attackQueue.peek();
    }
    public Building getOpponentBuilding() {return buildingAttackQueue.peek();}

    public void freeAttackQueue() {
        attackQueue = new LinkedList<>();
        buildingAttackQueue = new LinkedList<>();
    }
    public boolean isReadyToAttack() {
       if (attackQueue.isEmpty())
           return false;
       setAttackQueue(attackQueue.peek());
       return moveQueue.size() <= getSpeed();
    }

    public boolean isReadyToAttackBuilding() {
        if (buildingAttackQueue.isEmpty())
            return false;
        setAttackQueueBuilding(buildingAttackQueue.peek());
        return moveQueue.size() <= getSpeed();
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



