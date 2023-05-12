package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceExtractorBuilding extends WorkingBuilding {

    ArrayList<Resource> extractedResource;

    int rate;

    protected ResourceExtractorBuilding(int size,
                                        int hp,
                                        BuildingCategory category,
                                        BuildingName name,
                                        HashMap<ResourcesName, Integer> price) {

        super(size, hp, category, name, price);
    }

    public ArrayList<Resource> getExtractedResource() {
        return extractedResource;
    }

    public void setExtractedResource(ArrayList<Resource> extractedResource) {
        this.extractedResource = extractedResource;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
