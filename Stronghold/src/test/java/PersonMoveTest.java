import model.Game;
import model.environment.buildings.Building;
import model.environment.buildings.enums.BuildingName;
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
        new Building(BuildingName.CASTLE, government, map.getBlockByXY(8,1));
        new Building(BuildingName.LARGE_STONE_GATEHOUSE, government, map.getBlockByXY(0,0));

        System.out.println(map.showMap(map.getBlockByXY(5, 2), 5, 2));

        assertFalse(person.findPath(map.getBlockByXY(4,2)));

        person.move();
        System.out.println(person.getBlock());

        map.setTexture(3,1, Texture.GROUND);

        System.out.println(map.showMap(map.getBlockByXY(6, 1), 6, 1));

        assertTrue(person.findPath(map.getBlockByXY(15,15)));

        person.move();
        System.out.println(person.getBlock());
        System.out.println(map.showMap(map.getBlockByXY(7, 1), 6, 1));
        person.move();
        System.out.println(person.getBlock());
        person.move();
        System.out.println(person.getBlock());

//        assertTrue(person.findPath());
    }

}
