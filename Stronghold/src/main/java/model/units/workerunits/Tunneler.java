package model.units.workerunits;

import model.map.Block;
import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.units.Person;
import model.units.enums.UnitNames;

import java.util.ArrayList;
import java.util.HashMap;

public class Tunneler extends Person {

    public Tunneler(int hp, int speed, int defencePower, ArrayList<Armour> armour, UnitNames name, HashMap<ResourcesName, Integer> price, boolean canClimbLadder, boolean canDigMoat) {
        super(hp, speed, defencePower, armour, name, price, canClimbLadder, canDigMoat);
    }

    public void makeTunnel(Block block){

    }
}
