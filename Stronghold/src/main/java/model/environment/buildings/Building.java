package model.environment.buildings;

import model.environment.Environment;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.resourecs.Armour;
import model.resourecs.ResourcesName;
import model.society.Government;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Building extends Environment {

    protected Government owner;
    protected int hp;
    protected BuildingCategory category;
    protected BuildingName name;
    protected HashMap<ResourcesName, Integer> price;

    protected static ArrayList<Building> allBuildings = new ArrayList<>();

    static {
        ArrayList<String[]> resourceCsv = DataManager.getArrayListFromCSV(DataManager.BUILDINGS_PATH);
        String[] attributeNames = resourceCsv.get(0);

        start:
        for (int i = 1; i < resourceCsv.size(); i++) {

            String[] attributes = resourceCsv.get(i);
            String kind = "";
            int hp = 0, size = 0;
            BuildingCategory buildingCategory = null;
            BuildingName name = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();

            for (int j = 0; j < attributeNames.length; j++) {
                switch (attributeNames[j]) {
                    case "Kind": {
                        kind = attributes[j];
                        break;
                    }
                    case "Name": {
                        name = BuildingName.getBuildingNameByName(attributes[j]);
                        break;
                    }
                    case "Category": {
                        buildingCategory = BuildingCategory.getCategoryByName(attributes[j]);
                        break;
                    }
                    case "Size": {
                        size = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Hp": {
                        hp = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Price Kind": {
                        if (attributes[j].equals("null"))
                            continue;
                        String[] priceKinds = attributes[j].split("~");
                        String[] priceCounts = attributes[j + 1].split("~");

                        for (int i1 = 0; i1 < priceKinds.length && i1 < priceCounts.length; i1++) {
                            price.put(
                                    ResourcesName.getResourceByName(priceKinds[i1]),
                                    Integer.parseInt(priceCounts[i1])
                            );
                        }
                        break;
                    }
                }
            }
            switch (kind) {
                case "Gate": {
                    new Gate(size, hp, buildingCategory, name, price, 0);
                    break;
                }
                case "Bridge": {
                    new Bridge(size, hp, buildingCategory, name, price);
                    break;
                }
                case "Building": {
                    new Building(size, hp, buildingCategory, name, price);
                    break;
                }
                case "Church": {
                    new Church(size, hp, buildingCategory, name, price);
                    break;
                }
                case "Defensive": {
                    new DefensiveBuilding(size, hp, buildingCategory, price, name,
                            0, 0, 0);
                    break;
                }
                case "Extractor": {
                    new ResourceExtractorBuilding(size, hp, buildingCategory, name, price);
                    break;
                }
                case "Resource Maker": {
                    new ResourceMakerBuilding(size, hp, buildingCategory, name, price);
                    break;
                }
                case "Storage": {
                    new StorageBuilding(size, hp, buildingCategory, name, price, 0);
                    break;
                }
                case "Trap": {
                    new Traps(size, hp, buildingCategory, name, price);
                    break;
                }
                case "Unit Maker": {
                    new UnitMakerBuilding(size, hp, buildingCategory, name, price);
                    break;
                }
                case "House": {
                    new HouseBuilding(size, hp, buildingCategory, name, price, 0);
                    break;
                }
                case "Inn": {
                    new Inn(size, hp, buildingCategory, name, price);
                    break;
                }
                case "Shop": {
                    new Shop(size, hp, buildingCategory, name, price);
                    break;
                }
                default:
                    throw new RuntimeException();
            }
        }
    }

    protected Building(int size,
                       int hp,
                       BuildingCategory category,
                       BuildingName name,
                       HashMap<ResourcesName, Integer> price) {
        super(size);
        this.hp = hp;
        this.category = category;
        this.name = name;
        this.price = price;

        allBuildings.add(this);
    }

    protected Building getBuildingByBuildingName(BuildingName name) {
        for (Building building : allBuildings) {
            if (building.name.equals(name))
                return building;
        }
        return null;
    }

    public Government getOwner() {
        return owner;
    }

    public void setOwner(Government owner) {
        this.owner = owner;
    }

    public HashMap<ResourcesName, Integer> getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name + "{" +
                "class=" + this.getClass().getSimpleName() +
                ", hp=" + hp +
                ", category=" + category +
                ", price=" + price +
                "}\n";
    }

    public static void main(String[] args) {
        System.out.println(allBuildings);
    }
}
