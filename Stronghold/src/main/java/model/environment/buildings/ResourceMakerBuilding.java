package model.environment.buildings;

import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.ResourceHolder;
import model.resourecs.ResourcesName;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceMakerBuilding extends ResourceExtractorBuilding {

    ArrayList<ResourceHolder> inputResourceHolder;

    protected ResourceMakerBuilding(int hp,
                                    BuildingCategory category,
                                    BuildingName name,
                                    HashMap<ResourcesName, Integer> price) {
        super(hp, category, name, price);
    }

    public ArrayList<ResourceHolder> getInputResource() {
        return inputResourceHolder;
    }

    public void setInputResource(ArrayList<ResourceHolder> inputResourceHolder) {
        this.inputResourceHolder = inputResourceHolder;
    }
}
