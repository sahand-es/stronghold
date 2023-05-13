package controller;

import model.Game;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.society.Trade;
import view.enums.messages.TradeMessages;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeControl
{
    public static TradeMessages makeTrade(Game game, String targetColor, String resourceName, int amount , int gold){
        Government currentGovernment = game.getCurrentGovernment();
        Government targetGovernment = game.getGovernmentByColor(targetColor);
        if(targetGovernment == null)
            return TradeMessages.INVALID_COLOR;

        if(!ResourcesName.isValidName(resourceName))
            return TradeMessages.INVALID_RESOURCE;

        ResourcesName resource = ResourcesName.getResourceByName(resourceName);

        Trade trade = new Trade(currentGovernment,resource,amount,gold);
        game.addTrade(trade);
        currentGovernment.addTrade(trade);

        return TradeMessages.SUCCESSFUL;
    }

    public static TradeMessages acceptTrade(Game game,int id) {
        Trade trade = game.getTradeById(id);
        if (trade == null)
            return TradeMessages.TRADE_ID_DOESNT_EXIST;

        if (!(game.getCurrentGovernment()).getResource().checkPay(trade.getPrice()))
            return TradeMessages.NOT_ENOUGH_RESOURCES;

        game.getCurrentGovernment().getResource().pay(trade.getPrice());
        trade.getOwner().getResource().add(trade.getPrice());

        game.getCurrentGovernment().getResource().addGold(trade.getGold());
        trade.getOwner().getResource().addGold(-trade.getGold());

        game.removeTrade(trade);
        game.getCurrentGovernment().addTrade(trade);

        return TradeMessages.SUCCESSFUL;
    }

    public static String displayTrades(ArrayList<Trade> trades){
        String output = "Trades:";

        for (Trade trade : trades) {
            output += "\nid) " + trade.getId() + " government: " + trade.getOwner().getColor().getColorName() + " | " ;
            output += trade.getPrice().get(trade.getResource()) + " " + trade.getResource().name() + " gold = " + trade.getGold();
        }

        return output;
    }
}
