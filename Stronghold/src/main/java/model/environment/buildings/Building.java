package model.environment.buildings;

import model.environment.Environment;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.resource.ResourcesName;
import model.society.Government;
import model.units.Person;
import model.units.enums.UnitName;
import utility.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Building extends Environment {

    protected Government government;
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
            int capacity = 0;
            int fireRange = 0, defenceRange = 0, damage = 0;
            BuildingCategory buildingCategory = null;
            BuildingName name = null;
            HashMap<ResourcesName, Integer> price = new HashMap<>();
            ArrayList<ResourcesName> extract = new ArrayList<>();

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
                    case "Capacity" : {
                        if (attributes[j].equals("null"))
                            continue;
                        capacity = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Fire Range" : {
                        if (attributes[j].equals("null"))
                            continue;
                        fireRange = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Defence Range" : {
                        if (attributes[j].equals("null"))
                            continue;
                        defenceRange = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Damage" : {
                        if (attributes[j].equals("null"))
                            continue;
                        damage = Integer.parseInt(attributes[j]);
                        break;
                    }
                    case "Extracted Resource": {
                        if (attributes[j].equals("null"))
                            continue;
                        String[] extractNames = attributes[j].split("~");
                        for (String extractName : extractNames) {
                            extract.add(ResourcesName.getResourceByName(extractName));
                        }
                        break;
                    }

                }
            }
            switch (kind) {
                case "Gate": {
                    new Gate(hp, buildingCategory, name, price, capacity);
                    break;
                }
                case "Bridge": {
                    new Bridge(hp, buildingCategory, name, price);
                    break;
                }
                case "Building": {
                    new Building(hp, buildingCategory, name, price);
                    break;
                }
                case "Church": {
                    new Church(hp, buildingCategory, name, price);
                    break;
                }
                case "Defensive": {
                    new DefensiveBuilding(hp, buildingCategory, price, name,
                            fireRange, defenceRange, damage);
                    break;
                }
                case "Extractor": {
                    new ResourceExtractorBuilding(hp, buildingCategory, name, price, extract);
                    break;
                }

                case "Storage": {
                    new StorageBuilding(hp, buildingCategory, name, price, capacity);
                    break;
                }
                case "Trap": {
                    new Traps(hp, buildingCategory, name, price);
                    break;
                }
                case "Unit Maker": {
                    new UnitMakerBuilding(hp, buildingCategory, name, price);
                    break;
                }
                case "House": {
                    new HouseBuilding(hp, buildingCategory, name, price, capacity);
                    break;
                }
                case "Inn": {
                    new Inn(hp, buildingCategory, name, price);
                    break;
                }
                case "Shop": {
                    new Shop(hp, buildingCategory, name, price);
                    break;
                }

                case "Castle": {
                    new Castle(hp, buildingCategory, name, price);
                    break;
                }
                default:
                    throw new RuntimeException();
            }
        }
    }

    protected Building(int hp,
                       BuildingCategory category,
                       BuildingName name,
                       HashMap<ResourcesName, Integer> price) {
        this.hp = hp;
        this.category = category;
        this.name = name;
        this.price = price;

        allBuildings.add(this);
    }

    public BuildingCategory getCategory() {
        return category;
    }

    public Building(BuildingName name, Government government, Block block) {
        Building buildingToClone = getBuildingByBuildingName(name);
        this.hp = buildingToClone.hp;
        this.category = buildingToClone.category;
        this.name = buildingToClone.name;
        this.price = buildingToClone.price;
        this.government = government;
        super.setBlock(block);
        block.setEnvironment(this);
        government.addBuilding(this);
    }

    public static Building getBuildingByBuildingName(BuildingName name) {
        for (Building building : allBuildings) {
            if (building.name.equals(name))
                return building;
        }
        return null;
    }

    public Government getGovernment() {
        return government;
    }

    public HashMap<ResourcesName, Integer> getPrice() {
        return price;
    }

    public BuildingName getName() {
        return name;
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

    public boolean canPassBuilding(Person person){
        if(person.getName().equals(UnitName.ASSASSIN))
            return true;

        if(this.category.equals(BuildingCategory.CASTLE))
            return false;

        return true;
    }

    public void repair() {
        hp = getBuildingByBuildingName(name).hp;
    }

    public void die(){
        government.removeBuilding(this);
        this.getBlock().setEnvironment(null);
    }
}
