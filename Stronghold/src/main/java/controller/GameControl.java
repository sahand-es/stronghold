package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.App;
import model.Game;
import model.environment.Rock;
import model.environment.TreeClass;
import model.environment.buildings.*;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.*;
import model.resource.ResourcesName;
import model.society.Government;
import model.units.Person;
import model.units.Soldier;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitName;
import model.units.workerunits.Engineer;
import model.units.workerunits.Ladderman;
import model.units.workerunits.Tunneler;
import utility.RandomGenerators;
import view.GameViewController;
import view.enums.messages.GameMessages;
import view.shape.map.MapTile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class GameControl {
    private static Game game;
    private static Map map;
    private static Person selectedUnit = null;
    private static Building selectedBuilding = null;
    private static Government currentGovernment;

    // TODO: 7/2/2023 set when server called create building 
    private static String buildingId;
    private static String personId;

    public static void setBuildingId(String buildingId) {
        GameControl.buildingId = buildingId;
    }

    public static void setPersonId(String personId) {
        GameControl.personId = personId;
    }

    public GameControl(Game game) {
        // TODO: 6/7/2023 add gameViewController init
        setGame(game);
        currentGovernment = game.getCurrentGovernment();
    }

    // map:
    public static GameMessages setTexture(int x, int y, String texture) {
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!Texture.isValid(texture))
            return GameMessages.INVALID_TEXTURE;

        map.setTexture(x, y, Texture.getTextureByString(texture));

        return GameMessages.SUCCESS;
    }

    private static String makeItHashMap(ArrayList<String> commandName, ArrayList<String> command) {
        HashMap<String, String> data = new HashMap<>();
        data.put("command", "game");
        for (int i = 0; i < commandName.size(); i++) {
            data.put(commandName.get(i), command.get(i));
        }

        return new Gson().toJson(data, new TypeToken<HashMap<String, String>>() {
        }.getType());
    }

    public static void setTexture(ArrayList<XY> xyArrayList, Texture texture) {
        GameViewController.getMapPane().setSelected(xyArrayList);
        GameViewController.setTexture(texture);
    }

    public static void setTexture(Texture texture) {
        // TODO: 7/2/2023

        if (true) {
            ArrayList<XY> xyArrayList = new ArrayList<>();
            for (MapTile selectedTile : GameViewController.getMapPane().getSelectedTiles()) {
                xyArrayList.add(new XY(selectedTile.getBlock().getX(), selectedTile.getBlock().getY()));
            }


            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();

            commands.add("subcommand");
            data.add("setTexture");
            commands.add("xy");
            data.add(XY.toJson(xyArrayList));
            commands.add("texture");
            data.add(texture.getName());

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        GameViewController.setTexture(texture);
    }

    public static GameMessages clearBlock(int x, int y) {
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;


        map.getBlockByXY(x, y).clearBlock();
        return GameMessages.SUCCESS;
    }

    public static GameMessages setGroupTexture(int x1, int y1, int x2, int y2, String texture) {
        if (!map.isValidXY(x1, y1) || !map.isValidXY(x2, y2))
            return GameMessages.INVALID_XY;
        if (x1 > x2 || y1 > y2)
            return GameMessages.INVALID_RECTANGLE;
        if (!Texture.isValid(texture))
            return GameMessages.INVALID_TEXTURE;

        map.setGroupTexture(x1, y1, x2, y2, Texture.getTextureByString(texture));
        return GameMessages.SUCCESS;
    }

    public static GameMessages dropTree(int x, int y) {
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        new TreeClass(map.getBlockByXY(x, y));

        return GameMessages.SUCCESS;
    }

    public static GameMessages dropRock(int x, int y) {
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        new Rock(map.getBlockByXY(x, y));

        return GameMessages.SUCCESS;
    }

    public static void setGame(Game theGame) {
        try {
            GameViewController.setGameViewController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game = theGame;
        map = game.getMap();
        currentGovernment = game.getCurrentGovernment();
    }


    public static GameMessages checkFoodRate(int rate) {
        if (rate < -2 || rate > 2)
            return GameMessages.INVALID_RATE;
        currentGovernment.setFoodRate(rate);
        return GameMessages.SUCCESS;
    }

    public static GameMessages checkFearRate(int rate) {
        if (rate < -5 || rate > 5)
            return GameMessages.INVALID_RATE;

        currentGovernment.setFearRate(rate);
        return GameMessages.SUCCESS;
    }

    public static GameMessages checkTaxRate(int rate) {
        if (rate < -3 || rate > 8)
            return GameMessages.INVALID_RATE;

        currentGovernment.setTaxRate(rate);
        return GameMessages.SUCCESS;
    }

    public static GameMessages createBuilding(int x, int y, String type) {
        Building building = null;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!BuildingName.isValidName(type))
            return GameMessages.INVALID_BUILDING_TYPE;
        building = Building.getBuildingByBuildingName(BuildingName.getBuildingNameByName(type));
        if (!currentGovernment.getResource().checkPay(building.getPrice()))
            return GameMessages.NOT_ENOUGH_RESOURCE;
        if (!map.getBlockByXY(x, y).canBuildOnThis(BuildingName.getBuildingNameByName(type)))
            return GameMessages.CANNOT_BUILD_HERE;

        BuildingName buildingName = BuildingName.getBuildingNameByName(type);
        Block block = map.getBlockByXY(x, y);

        building = buildingConstructorCaller(buildingName, block);
        currentGovernment.getResource().pay(building.getPrice());

        GameViewController.addBuilding(building);

        return GameMessages.SUCCESS;
    }

    public static GameMessages dropBuilding(int x, int y, String type) {
        // TODO: 7/2/2023  
        Building building = null;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!BuildingName.isValidName(type))
            return GameMessages.INVALID_BUILDING_TYPE;
        building = Building.getBuildingByBuildingName(BuildingName.getBuildingNameByName(type));

        BuildingName buildingName = BuildingName.getBuildingNameByName(type);
        Block block = map.getBlockByXY(x, y);

        building = buildingConstructorCaller(buildingName, block);
        // TODO: 7/2/2023 if did not have access:
        building.setId(buildingId);

        GameViewController.addBuilding(building);


        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("dropBuilding");
            commands.add("xy");
            data.add(new XY(x, y).toJson());
            commands.add("type");
            data.add(type);
            commands.add("buildingId");
            data.add(building.getId());

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return GameMessages.SUCCESS;
    }

    private static Building buildingConstructorCaller(BuildingName buildingName, Block block) {
        switch (buildingName.kind) {
            case "Gate": {
                return new Gate(buildingName, currentGovernment, block);
            }
            case "Bridge": {
                return new Bridge(buildingName, currentGovernment, block);
            }
            case "Defencive": {
                return new DefensiveBuilding(buildingName, currentGovernment, block);
            }
            case "Storage": {
                return new StorageBuilding(buildingName, currentGovernment, block);
            }
            case "Unit Maker": {
                return new UnitMakerBuilding(buildingName, currentGovernment, block);
            }
            case "Trap": {
                return new Traps(buildingName, currentGovernment, block);
            }
            case "Extractor": {
                return new ResourceExtractorBuilding(buildingName, currentGovernment, block);
            }
            case "Church": {
                return new Church(buildingName, currentGovernment, block);
            }
            case "Inn": {
                return new Inn(buildingName, currentGovernment, block);
            }
            case "Shop": {
                return new Shop(buildingName, currentGovernment, block);
            }
            case "House": {
                return new HouseBuilding(buildingName, currentGovernment, block);
            }
            case "Castle": {
                return new Castle(buildingName, currentGovernment, block);
            }
            default: {
                return new Building(buildingName, currentGovernment, block);
            }
        }
    }

    public static GameMessages createUnit(String type, int count) {
        // TODO: 7/2/2023  
        Person person = null;
        if (selectedBuilding == null)
            return GameMessages.BUILDING_NOT_SELECTED;
        if (!(selectedBuilding instanceof UnitMakerBuilding))
            return GameMessages.NOT_UNIT_MAKER;
        if (!UnitName.isValidName(type))
            return GameMessages.INVALID_UNIT_TYPE;
        person = new Person(UnitName.getUnitByName(type));
        if (!currentGovernment.getResource().checkPay(person.getPrice(), count))
            return GameMessages.NOT_ENOUGH_RESOURCE;
        if (!UnitName.canThisBuildingMakeIt(selectedBuilding.getName(), person.getUnitName()))
            return GameMessages.CANNOT_MAKE_THIS_UNIT_IN_THIS_BUILDING;

        UnitName unitName = UnitName.getUnitByName(type);
        Block block = selectedBuilding.getBlock();

        currentGovernment.getResource().pay(person.getPrice(), count);

        for (int i = 0; i < count; i++) {
            person = personConstructorCaller(unitName, block);
        }

        GameViewController.addUnit(person);

        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("createUnit");
            commands.add("type");
            data.add(type);
            commands.add("unitId");
            data.add(person.getId());

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return GameMessages.SUCCESS;
    }

    public static GameMessages dropUnit(int x, int y, String type, int count) {
        Person person = null;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        Block block = map.getBlockByXY(x, y);
        if (!UnitName.isValidName(type))
            return GameMessages.INVALID_UNIT_TYPE;
        person = new Person(UnitName.getUnitByName(type));
        if (!block.canPassThisBlock(person))
            return GameMessages.CAN_NOT_DROP_UNIT_HERE;

        UnitName unitName = UnitName.getUnitByName(type);

        for (int i = 0; i < count; i++) {
            person = personConstructorCaller(unitName, block);
            GameViewController.addUnit(person);
        }

        // TODO: 7/2/2023 setid 

        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("dropUnit");
            commands.add("xy");
            data.add(new XY(x, y).toJson());
            commands.add("type");
            data.add(type);
            commands.add("unitId");
            data.add(person.getId());

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return GameMessages.SUCCESS;
    }

    private static Person personConstructorCaller(UnitName unitName, Block block) {
        Person person;
        switch (unitName.kind) {
            case "Soldier": {
                person = new Soldier(unitName, block, currentGovernment);
                break;
            }
            case "Tunneler": {
                person = new Tunneler(unitName, block, currentGovernment);
                break;
            }
            case "Engineer": {
                person = new Engineer(unitName, block, currentGovernment);
                break;
            }
            case "Ladderman": {
                person = new Ladderman(unitName, block, currentGovernment);
                break;
            }
            default: {
                person = new Person(unitName, block, currentGovernment);
                break;
            }
        }
        return person;
    }

    public static GameMessages selectBuilding(int x, int y) {
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (map.getBlockByXY(x, y).getEnvironment() == null)
            return GameMessages.EMPTY_XY_BUILDING;
        if (map.getBlockByXY(x, y).getEnvironment() instanceof Building) {
            Building building = (Building) map.getBlockByXY(x, y).getEnvironment();
            if (!building.getGovernment().equals(currentGovernment))
                return GameMessages.NOT_YOURS_BUILDING;
        } else {
            return GameMessages.NOT_A_BUILDING;
        }

        selectedBuilding = (Building) map.getBlockByXY(x, y).getEnvironment();

        if (selectedBuilding instanceof Shop)
            return GameMessages.MARKET_MENU;
        return GameMessages.SUCCESS;
    }

    public static String showSelectedUnitDetails() {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED.message();

        String output = selectedUnit.getUnitName() + ":\n" +
                "Government: " + selectedUnit.getGovernment().getColor().name() + "\n" +
                "Hp: " + selectedUnit.getHp() + "\n" +
                "Block: " + selectedUnit.getBlock() + "\n";

        return output;
    }
//ToDo: selected building change menu.

    public static GameMessages repair() {
        // TODO: 7/2/2023  
        if (!(selectedUnit instanceof Engineer))
            return GameMessages.SELECTED_UNIT_IS_NOT_ENGINEER;
        if (selectedBuilding == null)
            return GameMessages.BUILDING_NOT_SELECTED;
        if (!selectedBuilding.getCategory().equals(BuildingCategory.CASTLE) && !selectedBuilding.getPrice().containsKey(ResourcesName.STONE))
            return GameMessages.CANNOT_REPAIR_THIS_BUILDING_TYPE;
        if (!selectedBuilding.getGovernment().equals(selectedUnit.getGovernment()))
            return GameMessages.CAN_NOT_REPAIR_OPPONENT_BUILDING;
        HashMap<ResourcesName, Integer> stonePrice = new HashMap<>();
        if (selectedBuilding.getPrice().containsKey(ResourcesName.STONE)) {
            stonePrice.put(ResourcesName.STONE, selectedBuilding.getPrice().get(ResourcesName.STONE) / 2);
            if (!currentGovernment.getResource().checkPay(stonePrice))
                return GameMessages.NOT_ENOUGH_ROCK;
        }


        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("repair");

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        selectedBuilding.repair();
        return GameMessages.SUCCESS;
    }

    private static void deSelectBuilding() {
        selectedBuilding = null;
    }

    public static GameMessages selectUnit(int x, int y, int selectedCount) {
        Block block = null;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        block = map.getBlockByXY(x, y);
        if (block.getUnits().size() == 0)
            return GameMessages.EMPTY_XY_UNIT;
        if (!block.doesGovernmentHaveUnit(currentGovernment))
            return GameMessages.NOT_YOURS_UNIT;

        selectedUnit = block.selectUnit(currentGovernment, selectedCount);
        return GameMessages.SUCCESS;
    }

    public static void selectUnitByClick(Person person) {
        // TODO: 6/28/2023 delete:
        System.out.println("person selected: " + person.toString());
        if (!person.getGovernment().equals(currentGovernment))
            return;
        selectedUnit = person;

        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("selectUnit");
            commands.add("personId");
            data.add(person.getId());

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void selectBuildingByClick(Building building) {
        System.out.println("building selected: " + building.toString());
        selectedBuilding = building;
        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("selectUnit");
            commands.add("personId");
            data.add(building.getId());

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void deSelectUnit() {
        selectedUnit = null;
    }

    public static GameMessages moveUnit(int x, int y) {
        // TODO: 7/2/2023  
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!selectedUnit.findPath(map.getBlockByXY(x, y)))
            return GameMessages.CANNOT_GO_THERE;

        selectedUnit.stopPatroling();
        if (selectedUnit instanceof Soldier) {
            ((Soldier) selectedUnit).freeAttackQueue();
        }

        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("moveUnit");
            commands.add("xy");
            data.add(new XY(x, y).toJson());
            commands.add("type");

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return GameMessages.SUCCESS;
    }

    public static GameMessages patrolUnit(int x1, int y1, int x2, int y2) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!map.isValidXY(x1, y1) || !map.isValidXY(x2, y2))
            return GameMessages.INVALID_XY;
        if (!selectedUnit.canGoThere(map.getBlockByXY(x1, y1)) || !selectedUnit.canGoThere(map.getBlockByXY(x2, y2)))
            return GameMessages.CANNOT_GO_THERE;

        selectedUnit.setPatrol(map.getBlockByXY(x1, y1), map.getBlockByXY(x2, y2));
        return GameMessages.SUCCESS;
    }

    public static GameMessages setSoldierState(String state) {
        // TODO: 7/2/2023  
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!(selectedUnit instanceof Soldier))
            return GameMessages.THIS_UNIT_DOES_NOT_HAVE_STATE;

        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("setState");
            commands.add("state");
            data.add(state);

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ((Soldier) selectedUnit).setSoldierUnitState(SoldierUnitState.getByType(state));
        return GameMessages.SUCCESS;
    }

    public static GameMessages disbandUnit() {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;

        selectedUnit.findPath(currentGovernment.getCastle().getBlock());
        return GameMessages.SUCCESS;
    }

    public static GameMessages attack(int x, int y) {
        // TODO: 7/2/2023  
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!(selectedUnit instanceof Soldier))
            return GameMessages.CAN_NOT_ATTACK_WITH_THIS_UNIT_TYPE;
        Soldier soldier = (Soldier) selectedUnit;
        Block block = map.getBlockByXY(x, y);
        if (block.getEnemy(selectedUnit) == null)
            return GameMessages.NO_ENEMY_TO_ATTACK;
        if (!soldier.setAttackQueue(block.getEnemy(selectedUnit))) {
            return GameMessages.CAN_NOT_GO_THERE_TO_ATTACK;
        }
        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("dropBuilding");
            commands.add("xy");
            data.add(new XY(x, y).toJson());

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return GameMessages.SUCCESS;
    }

    public static GameMessages attackSelectedBuilding() {
        // TODO: 7/2/2023  
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!(selectedUnit instanceof Soldier))
            return GameMessages.CAN_NOT_ATTACK_WITH_THIS_UNIT_TYPE;
        if (selectedBuilding == null) {
            return GameMessages.BUILDING_NOT_SELECTED;
        }
        if (selectedBuilding.getGovernment().equals(currentGovernment))
            return GameMessages.CAN_NOT_ATTACK_YOUR_BUILDING;
        if (selectedBuilding.getHp() == Integer.MAX_VALUE)
            return GameMessages.CAN_NOT_ATTACK_THIS_BUILDING;

        Soldier soldier = (Soldier) selectedUnit;

        if (!soldier.setAttackQueueBuilding(selectedBuilding)) {
            return GameMessages.CAN_NOT_GO_THERE_TO_ATTACK;
        }
        if (true) {
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            commands.add("subcommand");
            data.add("attackSelectedBuilding");

            try {
                App.writeToServer(makeItHashMap(commands, data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        return GameMessages.SUCCESS;
    }

    public static GameMessages pourOil(String direction) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!(selectedUnit instanceof Engineer))
            return GameMessages.SELECTED_UNIT_IS_NOT_ENGINEER;
        if (!Direction.isValid(direction))
            return GameMessages.INVALID_DIRECTION;

        ((Engineer) selectedUnit).pourOil(Direction.getByString(direction));

        return GameMessages.SUCCESS;
    }

    public static GameMessages digTunnel(int x, int y) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!(selectedUnit instanceof Tunneler))
            return GameMessages.SELECTED_UNIT_IS_NOT_TUNNELER;
        Tunneler tunneler = (Tunneler) selectedUnit;
        if (!tunneler.canDigThere(map.getBlockByXY(x, y)))
            return GameMessages.CAN_NOT_DIG_THERE;

        tunneler.setTunnelQueue(map.getBlockByXY(x, y));

        return GameMessages.SUCCESS;
    }

    private static void attackControl() {
        for (Person unit : game.getAllUnits()) {
            if (!(unit instanceof Soldier))
                continue;
            Person closestPerson = findClosestEnemy(unit);
//attack
            if (closestPerson != null) {
                Soldier soldier = (Soldier) unit;
                if (soldier.getAttackQueue().isEmpty()) {
                    if (soldier.getSoldierUnitState().equals(SoldierUnitState.OFFENSIVE))
                        soldier.setAttackQueue(closestPerson);
                    if (soldier.getSoldierUnitState().equals(SoldierUnitState.DEFENSIVE)) {
                        if (unit.getBlock().distanceTo(closestPerson.getBlock()) < 15)
                            soldier.setAttackQueue(closestPerson);
                    }
                }
            }

//react to getting damage:
            Soldier soldier = (Soldier) unit;
            if (soldier.isReadyToAttack())
                if (soldier.getAttackQueue().peek() instanceof Soldier) {
                    Soldier lockedSoldier = (Soldier) soldier.getAttackQueue().peek();
                    if (true || !lockedSoldier.getSoldierUnitState().equals(SoldierUnitState.STANDING))
                        lockedSoldier.setAttackQueue(unit);
                }
        }
    }

    private static Person findClosestEnemy(Person person) {
        Person closestPerson = null;
        double minDistance = Double.MAX_VALUE;
        for (Person unit : game.getAllUnits()) {
            if (unit.getBlock().distanceTo(person.getBlock()) < minDistance) {
                if (!unit.getGovernment().equals(person.getGovernment())) {
                    minDistance = unit.getBlock().distanceTo(person.getBlock());
                    closestPerson = unit;
                }
            }
        }
        return closestPerson;
    }

    private static void extractControl() {
        for (Government government : game.getGovernments()) {
            for (Building building : government.getBuildings()) {
                if (building instanceof ResourceExtractorBuilding) {
                    ((ResourceExtractorBuilding) building).extract();
                }
            }
        }
    }

    private static void giveFood() {
        for (Government government : game.getGovernments()) {
            if (government.getResource().getFoodAmount() < government.foodUsage()) {
                currentGovernment.setFoodRate(-2);
            } else {
                government.getResource().payFood(government.foodUsage());
                government.addPopularity(government.getResource().getFoodDiversity());
            }
        }
    }

    private static void getTax() {
        for (Government government : game.getGovernments()) {
            if (government.getResource().getGold() < -1 * government.calcTax()) {
                government.setTaxRate(0);
            } else {
                government.getResource().addGold(government.calcTax());
            }
        }
    }

    private static void updatePopularity() {
        for (Government government : game.getGovernments()) {
            government.updatePopularityRate();
        }
    }

    private static void moveAllUnits() {
        for (Person unit : game.getAllUnits()) {
            GameViewController.moveUnit(unit, unit.move());
        }
    }

    private static void digAllTunnels() {
        for (Person unit : game.getAllUnits()) {
            if (unit instanceof Tunneler)
                ((Tunneler) unit).digTunnel();
        }
    }

    private static void attackBuildingFunction(Soldier soldier) {
        Building buildingToAttack;
        if (soldier.isReadyToAttackBuilding()) {
            buildingToAttack = soldier.getOpponentBuilding();
            buildingToAttack.takeDamage(soldier.getDamage() * 3);
            GameViewController.setFire(buildingToAttack);


            if (buildingToAttack.getHp() > 0) {
                soldier.setAttackQueueBuilding(buildingToAttack);
            } else {
                soldier.freeAttackQueue();
            }
        }

    }

    private static void attackFunction(Soldier soldier) {
        Person opponnet;
        if (soldier.isReadyToAttackAfterMove()) {
            opponnet = soldier.getOpponent();
            if (!(opponnet instanceof Soldier)) {
                opponnet.die();
                soldier.freeAttackQueue();
            }
            if (opponnet instanceof Soldier) {
                if (!((Soldier) opponnet).isReadyToAttackAfterMove()) {
                    opponnet.die();
                    soldier.freeAttackQueue();
                } else {
                    while (true) {
                        opponnet.takeDamage(soldier.getDamage());
                        soldier.takeDamage(((Soldier) opponnet).getDamage());

                        if (opponnet.getHp() == 0 && soldier.getHp() == 0) {
                            soldier.freeAttackQueue();
                            ((Soldier) opponnet).freeAttackQueue();
                            break;
                        }
                        if (opponnet.getHp() <= 0) {
                            soldier.freeAttackQueue();
                            break;
                        }
                        if (soldier.getHp() <= 0) {
                            ((Soldier) opponnet).freeAttackQueue();
                            break;
                        }
                    }
                }
            }
        }
    }

    public static GameMessages buildSiege(String buildName) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!(selectedUnit instanceof Engineer))
            return GameMessages.SELECTED_UNIT_IS_NOT_ENGINEER;
        if (!UnitName.isValidSiegeBuild(buildName))
            return GameMessages.INVALID_SIEGE_BUILD_NAME;
        UnitName unitName = UnitName.getUnitByName(buildName);
        if (!currentGovernment.doesHaveSiegeTent())
            return GameMessages.SIEGE_TENT_NOT_BUILD;
        if (!currentGovernment.getResource().checkPay(new Person(UnitName.getUnitByName(buildName)).getPrice()))
            return GameMessages.NOT_ENOUGH_RESOURCE;
        switch (unitName) {
            case CATAPULT:
            case STONE_THROWER:
            case BATTERING_RAM: {
                new Soldier(unitName, selectedUnit.getBlock(), currentGovernment);
                break;
            }
            case SIEGE_TOWER: {
                new Ladderman(unitName, selectedUnit.getBlock(), currentGovernment);
                break;
            }
        }
        return GameMessages.SUCCESS;
    }


    private static void doAttacks() {
        for (Person unit : game.getAllUnits()) {
            if (unit instanceof Soldier) {
                Soldier soldier = (Soldier) unit;
                attackFunction(soldier);
                attackBuildingFunction(soldier);
            }
        }
    }


    public static void nextTurn() {

        if (game.goToNextTurn()) {
            giveFood();
            getTax();
            extractControl();
            updatePopularity();
            moveAllUnits();
            attackControl();
            setTimeline();
            tileNextRound();
        }

        randomSickness();
        currentGovernment = game.getCurrentGovernment();
        deSelectBuilding();
        deSelectUnit();
        GameViewController.updateControlPanel();
    }

    private static void randomSickness() {
        // TODO: 7/2/2023 not 0 
        if (RandomGenerators.randomTrue(0)) {
            GameViewController.makeItSick(map.getBlockByXY(
                            RandomGenerators.randomNumber(0, map.getWidth() - 1),
                            RandomGenerators.randomNumber(0, map.getHeight()) - 1)
                    .getTile());
            currentGovernment.setPopularity(currentGovernment.getPopularity() - 2);
        }
    }

    private static void tileNextRound() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                map.getBlockByXY(x, y).getTile().nextRound();
            }
        }
    }

    private static void setTimeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {
            if (game.isMoveDone()) {
                doAttacks();
                digAllTunnels();
            }
        }));

        timeline.setOnFinished(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (game.isMoveDone())
                            return;
                        else {
                            timeline.playFromStart();
                        }
                    }
                }
        );
        timeline.play();
    }

    public static Government getCurrentGovernment() {
        return currentGovernment;
    }

    public static void setSelectedBuilding(Building selectedBuilding) {
        GameControl.selectedBuilding = selectedBuilding;
    }

    public static void selectBuildingById(String id) {
        for (Building building : game.getAllBuildings()) {
            if (building.getId().equals(id)) {
                selectedBuilding = building;
                return;
            }
        }
    }

    public static void selectUnitById(String id) {
        for (Person unit : game.getAllUnits()) {
            if (unit.getId().equals(id)) {
                selectedUnit = unit;
                return;
            }
        }
    }

    public static Person getSelectedUnit() {
        return selectedUnit;
    }

    public static Building getSelectedBuilding() {
        return selectedBuilding;
    }
}
