package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resourecs.ResourceHolder;
import model.resourecs.ResourcesName;
import model.society.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceExtractorBuilding extends Building {

    ArrayList<ResourcesName> extractedResources;

    int rate;

    protected ResourceExtractorBuilding(int hp,
                                        BuildingCategory category,
                                        BuildingName name,
                                        HashMap<ResourcesName, Integer> price,
                                        ArrayList<ResourcesName> extractedResources) {

        super(hp, category, name, price);
        this.extractedResources = extractedResources;
    }

    public ResourceExtractorBuilding(BuildingName name, Government government, Block block) {
        super(name, government, block);
        ResourceExtractorBuilding buildingToClone = (ResourceExtractorBuilding) getBuildingByBuildingName(name);
        this.rate = buildingToClone.getRate();
        this.extractedResources = buildingToClone.getExtractedResource();
    }

    public ArrayList<ResourcesName> getExtractedResource() {
        return extractedResources;
    }

    public void setExtractedResource(ArrayList<ResourcesName> extractedResourceHolder) {
        this.extractedResources = extractedResourceHolder;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


    public void extract() {
        HashMap<ResourcesName,Integer> extractPrice;
        HashMap<ResourcesName,Integer> product;
        for (ResourcesName resource : extractedResources) {
            extractPrice = ResourceHolder.getResourcePrice(resource);
            product = new HashMap<>();
            product.put(resource,rate);
            if (government.getResource().checkPay(extractPrice,rate)){
                government.getResource().pay(extractPrice,rate);
                government.getResource().add(product);
            }
        }
    }
    @Override
    public String toString() {
        return  this.name+"{" +
                "rate=" + rate +
                ", hp=" + hp +
                ", category=" + category +
                ", price=" + price +
                ", extractedResources=" + extractedResources +
                "}\n";
    }
}
