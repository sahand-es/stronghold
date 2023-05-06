package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.Resource;
import model.resourecs.ResourcesName;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceMakerBuilding extends ResourceExtractorBuilding {

    ArrayList<Resource> inputResource;

    protected ResourceMakerBuilding(int size,
                                    int hp,
                                    BuildingCategory category,
                                    BuildingName name,
                                    HashMap<ResourcesName, Integer> price) {
        super(size, hp, category, name, price);
    }

    public ArrayList<Resource> getInputResource() {
        return inputResource;
    }

    public void setInputResource(ArrayList<Resource> inputResource) {
        this.inputResource = inputResource;
    }
}
