package model.society;

import model.User;
import model.environment.buildings.Building;
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
    private int population;


    private int populationCapacity;
    private int foodCapacity;
    private int weaponCapacity;

    private int materialCapacity;


    private ArrayList<Trade> tradesDone;

    private Colors color;
    private User owner;

    public Government(User owner)
    {
        this.owner = owner;
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
}
