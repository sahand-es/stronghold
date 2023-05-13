import model.Game;
import model.environment.buildings.Building;
import model.map.Map;
import model.map.Texture;
import model.society.Government;
import model.society.enums.Colors;
import model.units.Person;
import model.units.enums.UnitName;
import org.junit.jupiter.api.Test;
import utility.DataManager;

import static org.junit.jupiter.api.Assertions.*;

public class PersonMoveTest {
    Map map = new Map(20, 20);
    Game game = new Game(map, 3);
    Government government = new Government(Colors.BLACK_COLOR, game);
    Person person = new Person(UnitName.ARABIAN_BOW, map.getBlockByXY(0, 0), government);


    @Test
    public void test1() {
        assertTrue(person.findPath(map.getBlockByXY(2,2)));

//        assertTrue(person.findPath(map.getBlockByXY(9,9)));
    }
//
    @Test
    public void test2() {
        map.setGroupTexture(3,0, 3, 2, Texture.DEEP_WATER);
        map.setGroupTexture(0,2, 3, 2, Texture.DEEP_WATER);


        System.out.println(map.showMap(map.getBlockByXY(5, 1)));

        assertFalse(person.findPath(map.getBlockByXY(4,2)));

        map.setTexture(3,1, Texture.GROUND);

        System.out.println(map.showMap(map.getBlockByXY(5, 1)));

        assertTrue(person.findPath(map.getBlockByXY(8,8)));
//        assertTrue(person.findPath());
    }

}
