package model.society;

import model.User;
import model.environment.buildings.Building;
import model.society.enums.Colors;
import model.resourecs.Resource;
import model.units.Person;


import java.util.ArrayList;

public class Government
{
    private int popularity;
    private int gold;
    private int population;
    private ArrayList<Resource> resources;
    private ArrayList<Resource> foods;
    private ArrayList<Building> buildings;
    private ArrayList<Person> allUnits;
    private ArrayList<Trade> tradesDone;
    private Colors color;
    private User owner;

    public Government(User owner)
    {
        this.owner = owner;
    }

    public int getPopularity()
    {
        return popularity;
    }

    public int getGold()
    {
        return gold;
    }

    public int getPopulation()
    {
        return population;
    }

    private static void popularityFactorControl(){

    }
    public void populationGrowth()
    {

    }

    public static void trade(Trade trade)
    {

    }
}
