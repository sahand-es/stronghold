package model.society;

import model.resourecs.Resource;

public class Trade
{
    String tradeId;
    Government seller;
    Government buyer;
    Resource resource;
    String message;
    int count;
    int price;

    public Trade(String tradeId, Government seller, Government buyer, Resource resource, String message, int count, int price)
    {
        this.tradeId = tradeId;
        this.seller = seller;
        this.buyer = buyer;
        this.resource = resource;
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

    public Resource getResource()
    {
        return resource;
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
