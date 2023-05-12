package controller;

import model.resourecs.ResourcesName;

import java.util.HashMap;

public class MarketControl
{
    static HashMap<ResourcesName,Integer> buyPrice;
    static HashMap<ResourcesName,Integer> sellPrice;

    static {
        buyPrice = new HashMap<>();
        sellPrice = new HashMap<>();

        for (ResourcesName food : ResourcesName.foods) {
            buyPrice.put(food,5);
        }

        for (ResourcesName weapon : ResourcesName.weapons) {
            buyPrice.put(weapon,20);
        }

        for (ResourcesName material : ResourcesName.Materials) {
            buyPrice.put(material,10);
        }

        for (ResourcesName food : ResourcesName.foods) {
            sellPrice.put(food,3);
        }

        for (ResourcesName weapon : ResourcesName.weapons) {
            sellPrice.put(weapon,15);
        }

        for (ResourcesName material : ResourcesName.Materials) {
            sellPrice.put(material,5);
        }
    }


}
