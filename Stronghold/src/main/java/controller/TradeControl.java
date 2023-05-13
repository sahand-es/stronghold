package controller;

import model.Game;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.society.Trade;
import view.enums.messages.TradeMessages;

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

        Trade trade = new Trade(currentGovernment,targetGovernment,resource,amount,gold);
        game.addTrade(trade);
        currentGovernment.addTrade(trade);
        targetGovernment.addTrade(trade);

        return TradeMessages.SUCCESSFUL;
    }

    public static TradeMessages acceptTrade(Game game,int id) {
        Trade trade = game.getTradeById(id);
        if (trade == null)
            return TradeMessages.TRADE_ID_DOESNT_EXIST;

        if (trade.isAccepted())
            return TradeMessages.TRADE_IS_ACCEPTED_BEFORE;

        if (!(trade.getDonator()).getResource().checkPay(trade.getPrice()))
            return TradeMessages.NOT_ENOUGH_RESOURCES;

    }
}
