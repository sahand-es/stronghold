package model.resourecs;

import java.util.HashMap;

public class Resource {
    private HashMap<ResourcesName, Integer> allResources;

    public Resource() {
        allResources = new HashMap<>();

        for (ResourcesName name : ResourcesName.values()) {
            allResources.put(name, 0);
        }
    }
}
