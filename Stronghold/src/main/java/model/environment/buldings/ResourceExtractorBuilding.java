package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceExtractorBuilding extends WorkingBuilding {

    ArrayList<Resource> extractedResource;

    int rate;

    protected ResourceExtractorBuilding(int size,
                                        int hp,
                                        BuildingCategory category,
                                        HashMap<ResourcesName, Integer> price) {

        super(size, hp, category, price);
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
