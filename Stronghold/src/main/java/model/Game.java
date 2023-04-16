package model;

import model.environment.Environment;
import model.map.Map;
import model.society.Government;
import model.society.Trade;
import model.resourecs.Resource;
import model.units.Person;

import java.util.ArrayList;

public class Game
{
    Map map;
    ArrayList<Government> governments;

    private final static ArrayList<Person> unitSample = new ArrayList<>();

    int turn;
    private final static ArrayList<Trade> allTrades = new ArrayList<>();
    private final static ArrayList <Resource> allResources = new ArrayList<>();

    private final static  ArrayList<Environment> allEnvironments =new ArrayList<>();


    private void runGame()
    {

    }

}
