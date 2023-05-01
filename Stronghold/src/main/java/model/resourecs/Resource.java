package model.resourecs;

import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Resource {
    protected final ResourcesName kind;
    protected int count;
    protected final HashMap<ResourcesName, Integer> price;
    protected int howManyFor1Price;
    protected static final ArrayList<Resource> allResources = new ArrayList<>();

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV(DataManager.RESOURCES_PATH);
        String[] attributeNames = resourceCsv.get(0);

        for (int i = 1; i < resourceCsv.size(); i++) {
            String[] attributes = resourceCsv.get(i);
            String name = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();
            int howMany = 0;
            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Type": {
//                        ToDo: if we want to have food class
                    }
                    case "Name": {
                        name = attributes[j];
                        break;
                    }
                    case "Price Kind": {
                        if (!attributes[j].equals("null")) {
                            price.put(ResourcesName.getResourceByName(attributes[j]), Integer.parseInt(attributes[j + 1]));
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

    protected Resource(String name, HashMap<ResourcesName, Integer> price, int howManyFor1Price) {
        kind = ResourcesName.getResourceByName(name);
        count = 0;
        this.price = price;
        this.howManyFor1Price = howManyFor1Price;

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
    public Resource(String name) {
        Resource resourceToClone = getResourceByName(name);
        assert resourceToClone != null;

        this.count = 0;
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

    public Resource(ResourcesName name) {
        Resource resourceToClone = getResourceByResourcesName(name);
        assert resourceToClone != null;

        this.count = 0;
        this.price = resourceToClone.getPrice();
        this.kind = resourceToClone.getKind();
    }

    Resource getResourceByName(String name) {
        for (Resource resource : allResources) {
            if (resource.getKind().equals(ResourcesName.getResourceByName(name)))
                return resource;
        }
        return null;
    }

     Resource getResourceByResourcesName(ResourcesName resourceName) {
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

    public void removeCount(int count) {
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
