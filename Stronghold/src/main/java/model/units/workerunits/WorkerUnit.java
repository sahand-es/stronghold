package model.units.workerunits;

import model.map.Block;
import model.resourecs.Armour;
import model.units.Person;

public class WorkerUnit extends Person
{

    public WorkerUnit(int hp, int speed, int defencePower, Armour armour, String name, Block block)
    {
        super(hp, speed, defencePower, armour, name, block);
    }


}