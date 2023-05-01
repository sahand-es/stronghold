package model.resourecs;

import model.society.enums.ResourcesName;

public class Weapon extends Resource{

    private int damage;
    private int attackRange;
    public Weapon(ResourcesName resource) {
        super(resource, 0);
    }
}
