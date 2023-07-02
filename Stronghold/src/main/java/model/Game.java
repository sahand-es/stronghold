package model;

import model.environment.buildings.Building;
import model.map.Map;
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


    public Game(Map map, Session session) {
        //todo game constructor must change
        this.map = map;
        int governmentCount = session.getUsers().size();

        for (int i = 0; i < governmentCount; i++) {
            governments.add(new Government(session.getUsers().get(i),Colors.colorsArr.get(i), this));
        }
//  Todo: change init with
            //map.initGovernments(governments);

            Database.addGame(this);
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Person> getAllUnits() {
        ArrayList<Person> units = new ArrayList<>();
        for (Government government : governments) {
            units.addAll(government.getUnits());
        }
        return units;
    }

    public ArrayList<Building> getAllBuildings() {
        ArrayList<Building> buildings = new ArrayList<>();
        for (Government government : governments) {
            buildings.addAll(government.getBuildings());
        }
        return buildings;
    }


    public void addUnit(Person unit) {
        allUnits.add(unit);
    }

    public void addBuilding(Building building) {
        allBuildings.add(building);
    }


    public void addTrade(Trade trade) {
        allTrades.add(trade);
    }

    public void removeUnit(Person unit) {
        for (Person person : allUnits) {
            if (person.equals(unit)) {
                allUnits.remove(person);
                return;
            }
        }
    }

    public void removeBuilding(Building building) {
        for (Building buildingToRemove : allBuildings) {
            if (buildingToRemove.equals(building)) {
                allBuildings.remove(buildingToRemove);
                return;
            }
        }
    }


    public void removeTrade(Trade trade) {
        allTrades.remove(trade);
    }

    public ArrayList<Government> getGovernments() {
        return governments;
    }

    public boolean goToNextTurn() {
        turn++;
        if (turn == governments.size()) {
            turn = 0;
            day++;
            return true;
        }
        return false;
    }

    public int getTurn() {
        return turn;
    }

    public int getDay() {
        return day;
    }

    public Government getCurrentGovernment() {
        return governments.get(turn);
    }

    public Government getGovernmentByColor(String name) {
        for (Government government : this.governments) {
            if (government.getColor().getColorName().equals(name))
                return government;
        }
        return null;
    }

    public void addTrades(Trade trade) {
        allTrades.add(trade);
    }

    public Trade getTradeById(int id) {
        for (Trade trade : allTrades) {
            if (trade.getId() == id)
                return trade;
        }
        return null;
    }

    public ArrayList<Trade> getAllTrades() {
        return allTrades;
    }

    public void removeGovernment(Government government) {
        governments.remove(government);
    }

    public boolean isMoveDone() {
        for (Person unit : allUnits) {
            if (unit.isMoving())
                return false;
        }
        return true;
    }
}
