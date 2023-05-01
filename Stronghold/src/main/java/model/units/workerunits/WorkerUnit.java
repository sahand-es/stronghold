package model.units.workerunits;

import model.map.Block;
import model.resourecs.Armour;
import model.units.Person;
import model.units.enums.UnitNames;

public class WorkerUnit extends Person
{

    public WorkerUnit(int hp, int speed, int defencePower, Armour armour, UnitNames name, Block block)
    {
        super(hp, speed, defencePower, armour, name, block);
    }


}
