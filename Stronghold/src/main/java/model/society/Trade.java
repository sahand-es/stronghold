package model.society;

import model.resourecs.ResourceHolder;

public class Trade
{
    String tradeId;
    Government seller;
    Government buyer;
    ResourceHolder resourceHolder;
    String message;
    int count;
    int price;

    public Trade(String tradeId, Government seller, Government buyer, ResourceHolder resourceHolder, String message, int count, int price)
    {
        this.tradeId = tradeId;
        this.seller = seller;
        this.buyer = buyer;
        this.resourceHolder = resourceHolder;
        this.message = message;
        this.count = count;
        this.price = price;
    }

    public Government getSeller()
    {
        return seller;
    }

    public Government getBuyer()
    {
        return buyer;
    }

    public ResourceHolder getResource()
    {
        return resourceHolder;
    }

    public String getMessage()
    {
        return message;
    }

    public int getCount()
    {
        return count;
    }

    public int getPrice()
    {
        return price;
    }
}
