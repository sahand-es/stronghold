package model.society;

import model.User;
import model.environment.buildings.Building;
import model.resourecs.Resource;
import model.siegeutil.SiegeUtil;
import model.society.enums.Colors;
import model.resourecs.ResourceHolder;
import model.units.Person;


import java.util.ArrayList;

public class Government
{
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int fearRate;



    private int populationCapacity;
    private int foodCapacity;
    private int weaponCapacity;
    private int materialCapacity;

    private final Resource resource;


    private final ArrayList<Trade> tradesDone;
    private final ArrayList<Person> units;
    private final ArrayList<SiegeUtil> siegeUtils;
    private final ArrayList<Building> buildings;

    private Colors color;
    private User owner;

    public Government(User owner)
    {
        this.owner = owner;
        tradesDone = new ArrayList<>();
        units = new ArrayList<>();
        siegeUtils = new ArrayList<>();
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

    public ArrayList<Trade> getTradesDone() {
        return tradesDone;
    }

    public ArrayList<Person> getUnits() {
        return units;
    }

    public ArrayList<SiegeUtil> getSiegeUtils() {
        return siegeUtils;
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
    }

    public void removeBuilding(Building building){
        buildings.remove(building);
    }

    public void addUnit(Person unit){
        units.add(unit);
    }

    public void removeUnit(Person unit){
        units.remove(unit);
    }

    public  void addSiegeUtil(SiegeUtil siegeUtil){
        siegeUtils.add(siegeUtil);
    }

    public void removeSiegeUtil(SiegeUtil siegeUtil){
        siegeUtils.remove(siegeUtil);
    }
}
