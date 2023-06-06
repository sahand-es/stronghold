package view;

import javafx.scene.Group;
import model.Game;
import model.map.MapPane;
import model.map.MapTile;
import model.society.Government;
import model.society.enums.Colors;
import model.units.Person;
import model.units.enums.UnitName;
import view.shape.PersonNode;

public class GameViewController {
    private static MapPane mapPane;
    private static Group allPersons;

    public static void setMapPane(MapPane mapPane) {
        GameViewController.mapPane = mapPane;
        allPersons = new Group();
        mapPane.getChildren().add(allPersons);
    }

    public static void addUnit(Person person) {
        PersonNode pn = new PersonNode(person);
        pn.setLayoutX(pn.getPerson().getBlock().getTile().getLayoutX() + MapTile.TILE_WIDTH * 0.1 + person.getBlock().getUnits().size() * 3);
        pn.setLayoutY(pn.getPerson().getBlock().getTile().getLayoutY() - MapTile.TILE_HEIGHT * 0.3 + person.getBlock().getUnits().size() * 2);
        allPersons.getChildren().add(pn);
    }
}
