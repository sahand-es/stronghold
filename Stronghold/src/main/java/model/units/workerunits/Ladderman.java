package model.units.workerunits;

import model.map.Block;
import model.resourecs.Armour;
import model.units.enums.UnitNames;

import java.util.ArrayList;

public class Ladderman extends WorkerUnit
{
    public Ladderman(int hp, int speed, int defencePower, ArrayList<Armour> armour, UnitNames name, boolean canClimbLadder, boolean canDigMoat) {
        super(hp, speed, defencePower, armour, name, canClimbLadder, canDigMoat);
    }

    public void putLadder()
    {
        
    }
}
