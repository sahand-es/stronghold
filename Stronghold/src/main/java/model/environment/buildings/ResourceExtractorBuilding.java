package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourceHolder;
import model.resourecs.ResourcesName;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceExtractorBuilding extends Building {

    ArrayList<ResourceHolder> extractedResourceHolder;

    int rate;

    protected ResourceExtractorBuilding(int hp,
                                        BuildingCategory category,
                                        BuildingName name,
                                        HashMap<ResourcesName, Integer> price) {

        super(hp, category, name, price);
    }

    public ArrayList<ResourceHolder> getExtractedResource() {
        return extractedResourceHolder;
    }

    public void setExtractedResource(ArrayList<ResourceHolder> extractedResourceHolder) {
        this.extractedResourceHolder = extractedResourceHolder;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
