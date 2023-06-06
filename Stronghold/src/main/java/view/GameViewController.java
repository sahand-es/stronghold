package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.map.MapPane;
import model.map.MapTile;
import model.units.Person;
import view.shape.PersonNode;

public class GameViewController {
    private static Pane mainPane;
    private static MapPane mapPane;
    private static Group allPersons;

    public static void setMapPane(MapPane mapPane, Pane mainPane) {
        GameViewController.mapPane = mapPane;
        GameViewController.mainPane = mainPane;
        allPersons = new Group();
        mapPane.getChildren().add(allPersons);
    }

    public static void addUnit(Person person) {
        PersonNode pn = new PersonNode(person);
        pn.setLayoutX(pn.getPerson().getBlock().getTile().getLayoutX() +
                MapTile.TILE_WIDTH * 0.1 + person.getBlock().getUnits().size() * 3);
        pn.setLayoutY(pn.getPerson().getBlock().getTile().getLayoutY() -
                MapTile.TILE_HEIGHT * 0.3 + person.getBlock().getUnits().size() * 2);
        allPersons.getChildren().add(pn);
    }

    public static void addNode(Node node) {
        if (!mainPane.getChildren().contains(node))
            mainPane.getChildren().add(node);
    }

    public static void removeNode(Node node) {
        mainPane.getChildren().remove(node);
    }
}
