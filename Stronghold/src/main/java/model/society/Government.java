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
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }
}
