package controller;

import model.Game;
import model.environment.buildings.Building;
import model.environment.buildings.UnitMakerBuilding;
import model.environment.buildings.enums.BuildingName;
import model.map.Map;
import model.society.Government;
import model.units.Person;
import model.units.enums.UnitName;
import view.enums.messages.GameMessages;
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
    private Person selectedUnit = null;
    private Building selectedBuilding = null;
    private static Government currentGovernment;

    public void setGame(Game game) {
        this.game = game;
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
        if (!map.getBlockByXY(x, y).canBuildOnThis())
            return GameMessages.CANNOT_BUILD_HERE;

        new Building(BuildingName.getBuildingNameByName(type), currentGovernment, map.getBlockByXY(x, y));
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

        for (int i = 0; i < count; i++) {
            new Person(UnitName.getUnitByName(type), selectedBuilding.getBlock(), currentGovernment);
        }
        return GameMessages.SUCCESS;
    }

    private GameMessages selectBuilding(int x, int y) {
        Map map = game.getMap();
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
        return GameMessages.SUCCESS;
    }

    private void deselectBuilding() {
        selectedBuilding = null;
    }

    private void nextTurn(){

        if(game.goToNextTurn()){
            for (Person unit : game.getAllUnits()) {
                unit.move();
                //todo attack
            }
        }
        currentGovernment = game.getCurrentGovernment();
    }

    public static Government getCurrentGovernment() {
        return currentGovernment;
    }
}
