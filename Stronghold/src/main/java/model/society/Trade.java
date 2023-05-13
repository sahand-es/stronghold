package model.society;

import model.resourecs.ResourcesName;

import java.util.HashMap;

public class Trade
{
    private Government requestor;
    private Government donator;
    private String message;
    private int gold;
    private int id;
    static int idSetter = 1;

    private boolean accepted;
    private HashMap<ResourcesName,Integer> price ;


    public Trade(Government seller, Government buyer, ResourcesName resource,int amount, int gold)
    {
        this.requestor = seller;
        this.donator = buyer;
        price = new HashMap<>();
        price.put(resource,amount);
        this.gold = gold;
        this.id = idSetter;
        idSetter++;
        accepted = false;
    }

    public Government getRequestor()
    {
        return requestor;
    }

    public Government getDonator()
    {
        return donator;
    }


    public String getMessage()
    {
        return message;
    }

    public int getId() {
        return id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public HashMap<ResourcesName, Integer> getPrice() {
        return price;
    }
}
