package model.society;

import model.resource.ResourcesName;

import java.util.HashMap;

public class Trade
{
    private Government owner;
    private String message;
    private int gold;
    private int id;
    static int idSetter = 1000;

    private ResourcesName resource;

    private HashMap<ResourcesName,Integer> price ;


    public Trade(Government seller, ResourcesName resource,int amount, int gold, String message)
    {
        this.owner = seller;
        this.resource = resource;
        this.message = message;
        price = new HashMap<>();
        price.put(resource,amount);
        this.gold = gold;
        this.id = idSetter;
        idSetter++;
    }

    public Government getOwner()
    {
        return owner;
    }


    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public ResourcesName getResource() {
        return resource;
    }

    public HashMap<ResourcesName, Integer> getPrice() {
        return price;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
