package model.society;

import model.Application;
import model.resourecs.ResourceHolder;
import model.resourecs.ResourcesName;

import java.util.HashMap;

public class Trade
{
    private Government seller;
    private Government buyer;
    private String message;
    private int gold;
    private int id;
    static int idSetter = 1;

    private boolean accepted;
    private HashMap<ResourcesName,Integer> price ;


    public Trade(Government seller, Government buyer, ResourcesName resource,int amount, int gold)
    {
        this.seller = seller;
        this.buyer = buyer;
        price = new HashMap<>();
        price.put(resource,amount);
        this.gold = gold;
        this.id = idSetter;
        idSetter++;
        accepted = false;
    }

    public Government getSeller()
    {
        return seller;
    }

    public Government getBuyer()
    {
        return buyer;
    }


    public String getMessage()
    {
        return message;
    }


    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
