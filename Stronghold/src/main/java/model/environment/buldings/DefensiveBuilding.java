package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;

import java.util.ArrayList;

public class DefensiveBuilding extends Building
{
    private int fireRange;
    private int defenceRange;

    private int attackPoint;

    private int defencePoint;

    public int getFireRange() {
        return fireRange;
    }

    public void setFireRange(int fireRange) {
        this.fireRange = fireRange;
    }

    public int getDefenceRange() {
        return defenceRange;
    }

    public void setDefenceRange(int defenceRange) {
        this.defenceRange = defenceRange;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefencePoint() {
        return defencePoint;
    }

    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }

    public DefensiveBuilding(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, int fireRange, int defenceRange, int attackPoint, int defencePoint) {
        super(name,size,category, hp,owner, price);
        this.fireRange = fireRange;
        this.defenceRange = defenceRange;
        this.attackPoint = attackPoint;
        this.defencePoint = defencePoint;
    }
}


