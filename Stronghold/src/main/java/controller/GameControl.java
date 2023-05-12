package controller;

import model.Game;
import model.environment.buildings.Building;
import model.map.Map;
import model.society.Government;
import model.units.Person;
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
    private Person selectedUnit;
    private Building selectedBuilding;
    private Government currentGovernment;

    public void setGame(Game game) {
        this.game = game;
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


}
