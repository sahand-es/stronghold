import controller.GameControl;
import model.Game;
import model.environment.buildings.Building;
import model.map.Map;
import model.map.Texture;
import model.society.Government;
import model.society.enums.Colors;
import model.units.Person;
import model.units.Soldier;
import model.units.enums.SoldierUnitState;
import model.units.enums.UnitName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameControlTest {
    Map map = new Map(60,60);
    Game game = new Game(map, 3);
    ArrayList<Government> governments = game.getGovernments();
    Government government1 = governments.get(0);
    Government government2 = governments.get(1);
    GameControl gameControl = new GameControl(game);

    @Test
    public void attackTestStanding() {
        Soldier soldier1 = new Soldier(UnitName.ARCHER, map.getBlockByXY(0, 0), government1);
        Soldier soldier2 = new Soldier(UnitName.ARCHER, map.getBlockByXY(4, 4), government2);
        System.out.println(soldier1.getHp() + ": " + soldier1.getGovernment().getColor().name());
        System.out.println(soldier2.getHp() + ": " + soldier2.getGovernment().getColor().name());


        gameControl.selectUnit(0, 0, 1);
        gameControl.attack(4,4);

        gameControl.nextTurn();
        gameControl.nextTurn();
        gameControl.nextTurn();

        System.out.println(soldier1.getHp());
        System.out.println(soldier2.getHp());
    }

    @Test
    public void attackTestOffensive() {
        Soldier soldier1 = new Soldier(UnitName.ARCHER, map.getBlockByXY(0, 0), government1);
        Soldier soldier2 = new Soldier(UnitName.ARCHER, map.getBlockByXY(4, 4), government2);
        System.out.println(soldier1.getHp() + ": " + soldier1.getGovernment().getColor().name());
        System.out.println(soldier2.getHp() + ": " + soldier2.getGovernment().getColor().name());


        gameControl.selectUnit(0, 0, 1);
        gameControl.setSoldierState("Offensive");

        gameControl.nextTurn();
        gameControl.nextTurn();
        gameControl.nextTurn();

        System.out.println(soldier1.getHp());
        System.out.println(soldier2.getHp());
    }
}
