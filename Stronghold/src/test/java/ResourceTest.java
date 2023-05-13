import model.Game;
import model.environment.buildings.Building;
import model.environment.buildings.enums.BuildingName;
import model.map.Map;
import model.map.Texture;
import model.resourecs.ResourcesName;
import model.society.Government;
import model.society.enums.Colors;
import model.units.Person;
import model.units.enums.UnitName;
import org.junit.jupiter.api.Test;
import utility.DataManager;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceTest {
    Map map = new Map(200,200);
    Game game = new Game(map,1);
    Government government = new Government(Colors.BLACK_COLOR,game);
    @Test
    public void payTest(){
        HashMap<ResourcesName, Integer> priceForTest = new HashMap<>();
        priceForTest.put(ResourcesName.GOLD, 150);
        priceForTest.put(ResourcesName.PEOPLE,2);

        government.getResource().pay(priceForTest);

        assertFalse(government.getResource().checkPay(priceForTest));
        assertEquals(-50, government.getResource().getGold());
        assertEquals(8,government.getResource().getPeople());
    }

}
