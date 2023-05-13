package controller;

import model.Game;
import model.environment.buildings.*;
import model.environment.buildings.enums.BuildingCategory;
import model.environment.buildings.enums.BuildingName;
import model.map.Block;
import model.map.Map;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.units.Person;
import model.units.Soldier;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitName;
import model.units.workerunits.Engineer;
import model.units.workerunits.Ladderman;
import model.units.workerunits.Tunneler;
import view.enums.messages.GameMessages;

import java.util.HashMap;
    /*
    TODO:
    1-game menu run ba government
    2-selects unit and building
    3-attack
    4-turn
    5-create building
 */

public class GameControl {
    private Game game;
    private Map map;
    private Person selectedUnit = null;
    private Building selectedBuilding = null;
    private Government currentGovernment;

    public void setGame(Game game) {
        this.game = game;
        this.map = game.getMap();
    }

    private GameMessages foodRate(int rate) {
        if (rate < -2 || rate > 2)
            return GameMessages.INVALID_RATE;
        currentGovernment.setFoodRate(rate);
        return GameMessages.SUCCESS;
    }

    private GameMessages fearRate(int rate) {
        if (rate < -5 || rate > 5)
            return GameMessages.INVALID_RATE;

        currentGovernment.setFearRate(rate);
        return GameMessages.SUCCESS;
    }

    private GameMessages taxRate(int rate) {
        if (rate < -3 || rate > 8)
            return GameMessages.INVALID_RATE;

        currentGovernment.setTaxRate(rate);
        return GameMessages.SUCCESS;
    }

    private GameMessages createBuilding(int x, int y, String type) {
        Building building = null;
        Map map = game.getMap();
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
//      Todo: add constructors
        switch (buildingName.kind) {
            case "Gate": {
                new Gate(buildingName, currentGovernment, block);
                break;
            }
            case "Bridge": {
                new Bridge(buildingName, currentGovernment, block);
                break;
            }
            case "Defencive": {
                new DefensiveBuilding(buildingName, currentGovernment, block);
                break;
            }
            case "Storage": {
                new StorageBuilding(buildingName, currentGovernment, block);
                break;
            }
            case "Unit Maker": {
                new UnitMakerBuilding(buildingName, currentGovernment, block);
                break;
            }
            case "Trap": {
                new Traps(buildingName, currentGovernment, block);
                break;
            }
            case "Extractor": {
                new ResourceExtractorBuilding(buildingName, currentGovernment, block);
                break;
            }
            case "Church": {
                new Church(buildingName, currentGovernment, block);
                break;
            }
            case "Inn": {
                new Inn(buildingName, currentGovernment, block);
                break;
            }
            case "Shop": {
                new Shop(buildingName, currentGovernment, block);
                break;
            }
            case "House": {
                new HouseBuilding(buildingName, currentGovernment, block);
                break;
            }
            case "Castle": {
                new Castle(buildingName, currentGovernment, block);
                break;
            }
            default: {
                new Building(buildingName, currentGovernment, block);
                break;
            }
        }
        return GameMessages.SUCCESS;
    }

    private GameMessages createUnit(String type, int count) {
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
        if (!UnitName.canThisBuildingMakeIt(selectedBuilding.getName(), person.getName()))
            return GameMessages.CANNOT_MAKE_THIS_UNIT_IN_THIS_BUILDING;

        UnitName unitName = UnitName.getUnitByName(type);
        Block block = selectedBuilding.getBlock();

        for (int i = 0; i < count; i++) {
            switch (unitName.kind) {
                case "Soldier": {
                    new Soldier(unitName, block, currentGovernment);
                    break;
                }
                case "Tunneler": {
                    new Tunneler(unitName, block, currentGovernment);
                    break;
                }
                case "Engineer": {
                    new Engineer(unitName, block, currentGovernment);
                    break;
                }
                case "Ladderman": {
                    new Ladderman(unitName, block, currentGovernment);
                    break;
                }
                default: {
                    new Person(unitName, block, currentGovernment);
                    break;
                }
            }
        }
        return GameMessages.SUCCESS;
    }

    private GameMessages selectBuilding(int x, int y) {
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
            return GameMessages.TRADE_MENU;
        return GameMessages.SUCCESS;
    }
//ToDo: selected building change menu.

    private GameMessages repair() {
        if (selectedBuilding == null)
            return GameMessages.BUILDING_NOT_SELECTED;
        if (!selectedBuilding.getCategory().equals(BuildingCategory.CASTLE) && !selectedBuilding.getPrice().containsKey(ResourcesName.STONE))
            return GameMessages.CANNOT_REPAIR_THIS_BUILDING_TYPE;
        HashMap<ResourcesName, Integer> stonePrice = new HashMap<>();
        stonePrice.put(ResourcesName.STONE, selectedBuilding.getPrice().get(ResourcesName.STONE) / 2);
        if (!currentGovernment.getResource().checkPay(stonePrice))
            return GameMessages.NOT_ENOUGH_ROCK;
//ToDo: close enemy

        selectedBuilding.repair();
        return GameMessages.SUCCESS;
    }

    private void deSelectBuilding() {
        selectedBuilding = null;
    }

    private GameMessages selectUnit(int x, int y, int selectedCount) {
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

    private void deSelectUnit() {
        selectedUnit = null;
    }

    private GameMessages moveUnit(int x, int y) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!selectedUnit.findPath(map.getBlockByXY(x, y)))
            return GameMessages.CANNOT_GO_THERE;

        return GameMessages.SUCCESS;
    }

    private GameMessages patrolUnit(int x1, int y1, int x2, int y2) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!map.isValidXY(x1, y1) || !map.isValidXY(x2, y2))
            return GameMessages.INVALID_XY;
        if (!selectedUnit.canGoThere(map.getBlockByXY(x1, y1)) || !selectedUnit.canGoThere(map.getBlockByXY(x2, y2)))
            return GameMessages.CANNOT_GO_THERE;

        selectedUnit.setPatrol(map.getBlockByXY(x1, y1), map.getBlockByXY(x2, y2));
        return GameMessages.SUCCESS;
    }

    private GameMessages setSoldierState(String state) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!(selectedUnit instanceof Soldier))
            return GameMessages.THIS_UNIT_DOES_NOT_HAVE_STATE;

        ((Soldier) selectedUnit).setSoldierUnitState(SoldierUnitState.getByType(state));
        return GameMessages.SUCCESS;
    }

    private GameMessages disbandUnit() {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;

        selectedUnit.findPath(currentGovernment.getCastle().getBlock());
        return GameMessages.SUCCESS;
    }

    private GameMessages attack(int x, int y) {
        if (selectedUnit == null)
            return GameMessages.UNIT_NOT_SELECTED;
        if (!map.isValidXY(x, y))
            return GameMessages.INVALID_XY;
        if (!(selectedUnit instanceof Soldier))
            return GameMessages.CAN_NOT_ATTACK_WITH_THIS_UNIT_TYPE;
        Soldier soldier = (Soldier) selectedUnit;
        Block block = map.getBlockByXY(x,y);
        if (block.getEnemy(selectedUnit) == null)
            return GameMessages.NO_ENEMY_TO_ATTACK;
        if (!soldier.setAttackQueue(block.getEnemy(selectedUnit))) {
            return GameMessages.CAN_NOT_GO_THERE_TO_ATTACK;
        }

        return GameMessages.SUCCESS;
    }

    private void attackControl() {
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
                    if (!lockedSoldier.getSoldierUnitState().equals(SoldierUnitState.STANDING))
                        lockedSoldier.setAttackQueue(unit);
                }
        }
    }

    private Person findClosestEnemy(Person person) {
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

    private void extractControl() {
        for (Building building : game.getAllBuildings()) {
            if (building instanceof ResourceExtractorBuilding) {
                ((ResourceExtractorBuilding) building).extract();
            }
        }
    }

    private void giveFood() {
        // TODO:
    }

    private void moveAllUnits() {
        for (Person unit : game.getAllUnits()) {
            unit.move();
        }
    }

    private void attackFunction(Soldier soldier) {
        Person opponnet;
        if (soldier.isReadyToAttack()) {
            opponnet = soldier.getOpponnet();
            if (!(opponnet instanceof Soldier)) {
                opponnet.die();
                soldier.freeAttackQueue();
            }
            if (opponnet instanceof Soldier) {
                if (!((Soldier) opponnet).isReadyToAttack()) {
                    opponnet.die();
                    soldier.freeAttackQueue();
                }
                else {
                    while (true) {
                        opponnet.takeDamage(soldier.getDamage());
                        soldier.takeDamage(((Soldier) opponnet).getDamage());

                        if (opponnet.getHp() < 0) {
                            soldier.freeAttackQueue();
                            break;
                        }
                        if (soldier.getHp() < 0) {
                            ((Soldier) opponnet).freeAttackQueue();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void doAttacks() {
        for (Person unit : game.getAllUnits()) {
            if (unit instanceof Soldier) {
                Soldier soldier = (Soldier) unit;
                attackFunction(soldier);
            }
        }
    }


    private void nextTurn() {
        if (game.goToNextTurn()) {
            attackControl();
            moveAllUnits();
            doAttacks();
        }
        currentGovernment = game.getCurrentGovernment();
    }
}
