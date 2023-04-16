package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;
import model.units.Person;

import java.util.ArrayList;

public class ResourceExtractorBuilding extends WorkingBuilding {

    ArrayList<Resource> extractedResource;

    int rate;

    public ResourceExtractorBuilding(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, ArrayList<Resource> extractedResource, int rate, ArrayList<Person> workers) {
        super(name ,size, category ,hp, owner, price,workers);
        this.extractedResource = extractedResource;
        this.rate = rate;
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
