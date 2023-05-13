package controller;

import model.Game;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.society.Trade;
import utility.CheckFunctions;
import view.enums.messages.TradeMessages;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeControl
{
    public static TradeMessages makeTrade(HashMap<String,String> data){
        String type = data.get("type");
        String price = data.get("price");
        String amount = data.get("amount");
        String tradeMessage = data.get("tradeMessage");

        if(type == null){
            return TradeMessages.TYPE_EMPTY;
        }
        if(price == null){
            return TradeMessages.PRICE_EMPTY;
        }
        if(amount == null){
            return TradeMessages.AMOUNT_EMPTY;
        }
        if(tradeMessage == null){
            return TradeMessages.MESSAGE_EMPTY;
        }

        if (CheckFunctions.checkPriceFormat(price)){
            return TradeMessages.INVALID_PRICE;
        }
        if (CheckFunctions.checkAmountFormat(amount)){
            return TradeMessages.INVALID_AMOUNT;
        }
        if (ResourcesName.isValidName(type)){
            return TradeMessages.INVALID_RESOURCE;
        }

        return TradeMessages.SUCCESS;
    }

    public static TradeMessages acceptTrade(Game game,int id) {
        Trade trade = game.getTradeById(id);
        if (trade == null)
            return TradeMessages.TRADE_ID_DOES_NOT_EXIST;

        if (!(game.getCurrentGovernment()).getResource().checkPay(trade.getPrice()))
            return TradeMessages.NOT_ENOUGH_RESOURCES;

        game.getCurrentGovernment().getResource().pay(trade.getPrice());
        trade.getOwner().getResource().add(trade.getPrice());

        game.getCurrentGovernment().getResource().addGold(trade.getGold());
        trade.getOwner().getResource().addGold(-trade.getGold());

        game.removeTrade(trade);
        game.getCurrentGovernment().addTrade(trade);

        return TradeMessages.SUCCESS;
    }

    public static String displayTrades(ArrayList<Trade> trades){
        String output = "";

        for (Trade trade : trades) {
            output += "\nid) " + trade.getId() + " government: " + trade.getOwner().getColor().getColorName() + " | " ;
            output += trade.getPrice().get(trade.getResource()) + " " + trade.getResource().name() + " gold = " + trade.getGold();
        }

        return output;
    }

    public static String showAllTrades(Game game){
        return "all trades:" + displayTrades(game.getAllTrades());
    }

    public static String showTradeHistory(Game game){
        return "trades history:" + displayTrades(game.getCurrentGovernment().getTradesHistory());
    }


}
