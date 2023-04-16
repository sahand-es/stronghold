package model.environment.buldings;

import model.environment.buldings.enums.BuildingCategory;
import model.resourecs.Resource;
import model.society.Government;
import model.units.Person;

import java.util.ArrayList;

public class ResourceMakerBuilding extends ResourceExtractorBuilding {

    ArrayList<Resource> inputResource;

    public ResourceMakerBuilding(String name, int size, BuildingCategory category, int hp, Government owner, ArrayList<Resource> price, ArrayList<Resource> extractedResource, int rate, ArrayList<Person> workers, ArrayList<Resource> inputResource) {
        super(name,size,category, hp, owner, price, extractedResource, rate,workers);
        this.inputResource = inputResource;
    }

    public ArrayList<Resource> getInputResource() {
        return inputResource;
    }

    public void setInputResource(ArrayList<Resource> inputResource) {
        this.inputResource = inputResource;
    }
}
