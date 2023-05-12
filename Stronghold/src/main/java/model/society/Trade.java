package model.society;

import model.resourecs.ResourceHolder;
import model.resourecs.ResourcesName;

public class Trade
{
    Government seller;
    Government buyer;
    ResourcesName resource;
    String message;
    int amount;
    int price;

    boolean accepted;

    public Trade(Government seller, Government buyer, ResourcesName resource, int count, int price)
    {
        this.seller = seller;
        this.buyer = buyer;
        this.resource = resource;
        this.amount = count;
        this.price = price;
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

    public ResourcesName getResource()
    {
        return resource;
    }

    public String getMessage()
    {
        return message;
    }

    public int getCount()
    {
        return amount;
    }

    public int getPrice()
    {
        return price;
    }
}
