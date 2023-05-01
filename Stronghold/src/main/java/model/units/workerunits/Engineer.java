package model.units.workerunits;

import model.map.Block;
import model.map.Directions;
import model.resourecs.Armour;
import model.units.enums.UnitNames;

public class Engineer extends WorkerUnit
{
    public Engineer(int hp, int speed, int defencePower, Armour armour, UnitNames name, Block block)
    {
        super(hp, speed, defencePower, armour, name, block);
    }

    public void pourOil(Directions direction)
    {

    }
    public void build()
    {

    }
}
