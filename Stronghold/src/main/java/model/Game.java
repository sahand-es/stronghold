package model;

import model.environment.buildings.Building;
import model.map.Map;
import model.siegeutil.SiegeUtil;
import model.society.Government;
import model.society.Trade;
import model.society.enums.Colors;
import model.units.Person;

import java.util.ArrayList;

public class Game {


    Map map;

    int turn = 0;
    int day = 1;
    private final ArrayList<Government> governments = new ArrayList<>();

    private final ArrayList<Person> allUnits = new ArrayList<>();
    private final  ArrayList<Trade> allTrades = new ArrayList<>();

    private final ArrayList<Building> allBuildings = new ArrayList<>();

    private final ArrayList<SiegeUtil> allSiegeUtil = new ArrayList<>();

    public Game(Map map, int governmentCount) {
        this.map = map;

        for (int i = 0; i < governmentCount; i++) {
            governments.add(new Government(Colors.colorsArr.get(i), this));

            map.initGovernments(governments);

            Application.addGame(this);
        }
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Person> getAllUnits() {
        return allUnits;
    }

    public ArrayList<Building> getAllBuildings() {
        return allBuildings;
    }

    public ArrayList<SiegeUtil> getAllSiegeUtil() {
        return allSiegeUtil;
    }

    public void addUnit(Person unit) {
        allUnits.add(unit);
    }

    public void addBuilding(Building building) {
        allBuildings.add(building);
    }

    public void addSiegeUtil(SiegeUtil siegeUtil) {
        allSiegeUtil.add(siegeUtil);
    }

    public void addTrade(Trade trade){
        allTrades.add(trade);
    }

    public void removeUnit(Person unit) {
        allUnits.remove(unit);
    }

    public void removeBuilding(Building building) {
        allBuildings.remove(building);
    }

    public void removeSiegeUtil(SiegeUtil siegeUtil) {
        allSiegeUtil.remove(siegeUtil);
    }

    public void removeTrade(Trade trade){
        allTrades.remove(trade);
    }

    public ArrayList<Government> getGovernments() {
        return governments;
    }

    public boolean goToNextTurn(){
        turn ++;
        if(turn > governments.size()){
            turn = 0;
            day++;
            return true;
        }
        return false;
    }

    public int getTurn() {
        return turn;
    }

    public int getDay(){
        return day;
    }

    public Government getCurrentGovernment(){
        return governments.get(turn);
    }

    public Government getGovernmentByColor(String name){
        for (Government government : this.governments) {
            if(government.getColor().getColorName().equals(name))
                return government;
        }
        return null;
    }

    public void addTrades(Trade trade){
        allTrades.add(trade);
    }

    public Trade getTradeById(int id){
        for (Trade trade : allTrades) {
            if(trade.getId() == id)
                return trade;
        }
        return null;
    }

    public ArrayList<Trade> getAllTrades() {
        return allTrades;
    }
}
