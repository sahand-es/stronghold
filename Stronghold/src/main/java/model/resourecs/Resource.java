package model.resourecs;

import model.society.enums.ResourcesName;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Resource {
    ResourcesName kind;
    int count;
    private final HashMap<ResourcesName, Integer> price;
    private int howManyFor1Price;
    private static final ArrayList<Resource> allResources = new ArrayList<>();

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV("src/main/resources/ResourceAndFood.csv");
        String[] attributeNames = resourceCsv.get(0);

        for (int i = 1; i < resourceCsv.size(); i++) {
            String[] attributes = resourceCsv.get(i);
            String name = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();
            int howMany = 0;
            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Type": {
                        if (false && !attributeNames[j].equals("Initial"))
                            continue;
                        break;
                    }
                    case "Name": {
                        name = attributes[j];
                        break;
                    }
                    case "Price Kind": {
                            if (!attributes[j].equals("null")) {
                                price.put(ResourcesName.getResourceByName(attributes[j]), Integer.parseInt(attributes[j+1]));
                        }
                            break;
                    }
                    case "Count": {
                        howMany = Integer.parseInt(attributes[j]);
                        break;
                    }
                }
            }

            new Resource(name, price, howMany);
        }


    }

    private Resource(String name, HashMap<ResourcesName, Integer> price, int howManyFor1Price) {
        kind = ResourcesName.getResourceByName(name);
        count = 0;
        this.price = price;

        allResources.add(this);
    }

    public Resource(String name, int count) {
        Resource resourceToClone = getResourceByName(name);
        assert resourceToClone != null;

        this.count = count;
        this.howManyFor1Price = resourceToClone.howManyFor1Price;
        this.price = resourceToClone.price;
        this.kind = resourceToClone.kind;
    }

    public Resource(ResourcesName name, int count) {
        Resource resourceToClone = getResourceByResourcesName(name);
        assert resourceToClone != null;

        this.count = count;
        this.price = resourceToClone.getPrice();
        this.kind = resourceToClone.getKind();
    }

    private Resource getResourceByName(String name) {
        for (Resource resource : allResources) {
            if (resource.getKind().equals(ResourcesName.getResourceByName(name)))
                return resource;
        }
        return null;
    }

    private Resource getResourceByResourcesName(ResourcesName resourceName) {
        for (Resource resource : allResources) {
            if (resource.getKind().equals(resourceName))
                return resource;
        }
        return null;
    }

    public ResourcesName getKind() {
        return kind;
    }

    public int getCount() {
        return count;
    }

    public HashMap<ResourcesName, Integer> getPrice() {
        return price;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void removeCount(int count){
        this.count -= count;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "kind=" + kind +
                ", count=" + count +
                ", price=" + price +
                ", howManyFor1Price=" + howManyFor1Price +
                "}\n";
    }
}
