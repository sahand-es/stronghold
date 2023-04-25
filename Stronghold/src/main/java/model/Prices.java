package model;

import model.environment.buldings.Building;
import model.resourecs.Resource;
import model.units.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class Prices {
    HashMap<Resource, ArrayList<Resource>> resourcePrices;
    HashMap<Person, ArrayList<Resource>> soldierPrices;
    HashMap<Building, ArrayList<Resource>> buildingPrices;
}
