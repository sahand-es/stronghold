package model.resourecs;

import model.society.enums.Resources;

import java.util.ArrayList;

public class Weapon extends Resource{

    private int damage;
    private int attackRange;
    public Weapon(Resources resource) {
        super(resource);
    }
}
