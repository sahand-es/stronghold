package model.society;

import model.Game;
import model.environment.buildings.Building;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.Resource;
import model.society.enums.Colors;
import model.units.Person;


import java.util.ArrayList;

public class Government
{
//    ToDo: auto rate when there is not enough food
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int fearRate;



    private int populationCapacity;
    private int foodCapacity;
    private int weaponCapacity;
    private int materialCapacity;

    private final Resource resource;


    private final ArrayList<Trade> tradesHistory;
    private final ArrayList<Person> units;
    private final ArrayList<Building> buildings;

    private Colors color;
    private Game game;

    public Government(Colors color,Game game)
    {
        this.game = game;
        this.color = color;
        tradesHistory = new ArrayList<>();
        units = new ArrayList<>();
        buildings = new ArrayList<>();
        taxRate = 0;
        fearRate = 0;
        foodRate = 0;
        popularity = 50;
        resource = new Resource();
    }


    public void populationGrowth()
    {

    }

    public static void trade(Trade trade)
    {

    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
        popularity += 4 * foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
        popularity += this.calcPopularityOfTaxRate();
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
        popularity += fearRate;
    }


    public int getPopulationCapacity() {
        return populationCapacity;
    }

    public void setPopulationCapacity(int populationCapacity) {
        this.populationCapacity = populationCapacity;
    }

    public int getFoodCapacity() {
        return foodCapacity;
    }

    public void setFoodCapacity(int foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    public int getWeaponCapacity() {
        return weaponCapacity;
    }

    public void setWeaponCapacity(int weaponCapacity) {
        this.weaponCapacity = weaponCapacity;
    }

    public int getMaterialCapacity() {
        return materialCapacity;
    }

    public void setMaterialCapacity(int materialCapacity) {
        this.materialCapacity = materialCapacity;
    }

    public ArrayList<Trade> getTradesHistory() {
        return tradesHistory;
    }

    public ArrayList<Person> getUnits() {
        return units;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public Resource getResource() {
        return resource;
    }

    public int getPopulation(){
        return resource.getPeople() + units.size();
    }

    private int calcPopularityOfTaxRate(){
        int output;
        output = -2 * this.taxRate;
        if (this.taxRate <= 0)
            output++;
        return output;
    }

    public int calcTax(){
        int output = 0;
        if(this.taxRate == 0)
            return output;

        if (this.taxRate > 0){
            output = (int) (0.2f*taxRate +0.4f)*getPopulation();
        } else {
            output = (int) (0.2f*taxRate -0.4f)*getPopulation();
        }
        return output;
    }

    public void addBuilding(Building building){
        buildings.add(building);
        game.addBuilding(building);
    }

    public void removeBuilding(Building building){
        buildings.remove(building);
        game.removeBuilding(building);
    }

    public void addUnit(Person unit){
        units.add(unit);
        game.addUnit(unit);
    }

    public void removeUnit(Person unit){
        units.remove(unit);
        game.removeUnit(unit);
    }

    public void addPopularity(int amount){
        popularity += amount;
    }

    public void addCapacity(BuildingName name, int capacity){
        switch (name){
            case ARMOURY:
                weaponCapacity += capacity;
                break;

            case GRANARY:
                foodCapacity += capacity;
                break;

            case STOCKPILE:
                materialCapacity += capacity;
                break;

            default:
                break;
        }
    }

    public void subtractCapacity(BuildingName building, int capacity){
        switch (building){
            case ARMOURY:
                weaponCapacity -= capacity;
                break;

            case GRANARY:
                foodCapacity -= capacity;
                break;

            case STOCKPILE:
                materialCapacity -= capacity;
                break;

            default:
                break;
        }
    }

    public void addPopulationCapacity(int capacity){
        populationCapacity += capacity;
        resource.addPeople(capacity);
    }

    public void subtractPopulationCapacity(int capacity){
        populationCapacity -= capacity;
    }

    public String ratesShow() {
        StringBuilder output = new StringBuilder();
        output.append("Tax rate: ").append(taxRate);
        output.append("Fear rate: ").append(fearRate);
        output.append("Food rate: ").append(foodRate);

        return output.toString();
    }

    public Colors getColor() {
        return color;
    }

    public void addTrade(Trade trade){
        this.tradesHistory.add(trade);
    }

    @Override
    public String toString() {
        return " {" +
                "color=" + color +
                ", game=" + game +
                '}';
    }

    public void lose(){
        for (Person unit : units) {
            unit.die();
        }
        for (Building building : buildings) {
            building.die();
        }
        game.removeGovernment(this);
    }
}
