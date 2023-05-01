package model.units.workerunits;

import model.map.Block;
import model.resourecs.Armour;
import model.units.enums.UnitNames;

public class Ladderman extends WorkerUnit
{
    public Ladderman(int hp, int speed, int defencePower, Armour armour, UnitNames name, Block block)
    {
        super(hp, speed, defencePower, armour, name, block);
    }

    public void putLadder()
    {
        
    }
}
