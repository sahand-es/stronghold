package controller;

import model.Game;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.society.Trade;
import view.enums.messages.TradeMessages;

public class TradeControl
{
    public static TradeMessages makeTrade(Game game, Government currentGovernment, String targetColor, String resourceName, int amount , int gold){
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
}
