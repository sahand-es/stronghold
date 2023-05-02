package model.units.workerunits;

import model.map.Block;
import model.map.Directions;
import model.resourecs.Armour;
import model.units.enums.UnitNames;

import java.util.ArrayList;

public class Engineer extends WorkerUnit
{
    public Engineer(int hp, int speed, int defencePower, ArrayList<Armour> armour, UnitNames name, boolean canClimbLadder, boolean canDigMoat) {
        super(hp, speed, defencePower, armour, name, canClimbLadder, canDigMoat);
    }


    public void pourOil(Directions direction)
    {

    }
    public void build()
    {

    }
}
