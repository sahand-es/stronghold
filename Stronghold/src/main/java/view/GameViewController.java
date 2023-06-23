package view;

import javafx.animation.Animation;
import javafx.animation.Animation.*;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.environment.Environment;
import model.environment.buildings.Building;
import model.map.Block;
import model.map.MapPane;
import model.map.MapTile;
import model.units.Person;
import view.animation.MoveAnimation;
import view.shape.BuildingNode;
import view.shape.PersonNode;

import java.util.Queue;

public class GameViewController {
    private static Pane mainPane;
    private static MapPane mapPane;
    private static Group allPersons;
    private static Group allBuildings;

    public static void setMapPane(MapPane mapPane, Pane mainPane) {
        GameViewController.mapPane = mapPane;
        GameViewController.mainPane = mainPane;
        allPersons = new Group();
        allBuildings = new Group();
        mapPane.getChildren().addAll(allBuildings, allPersons);
    }

    public static void addNode(Node node) {
        if (!mainPane.getChildren().contains(node))
            mainPane.getChildren().add(node);
    }

    public static void removeNode(Node node) {
        mainPane.getChildren().remove(node);
    }

    public static void addUnit(Person person) {
        PersonNode pn = new PersonNode(person);
        pn.setLayoutX(getLayoutXForPerson(person.getBlock().getTile()));
        pn.setLayoutY(getLayoutYForPerson(person.getBlock().getTile()));
        allPersons.getChildren().add(pn);
    }

    public static double getLayoutXForPerson(MapTile tile) {
        return tile.getLayoutX() + MapTile.TILE_WIDTH * 0.1 + tile.getBlock().getUnits().size() * 3;
    }
    public static double getLayoutYForPerson(MapTile tile) {
        return tile.getLayoutY() -
                MapTile.TILE_HEIGHT * 0.3 + tile.getBlock().getUnits().size() * 2;
    }

    public static PersonNode getPersonNodeByPerson(Person person) {
        for (Node child : allPersons.getChildren()) {
            if (!(child instanceof PersonNode))
                return null;
            PersonNode pn = (PersonNode) child;
            if (pn.getPerson().equals(person))
                return pn;
        }
        return null;
    }

    public static void moveUnit(Person person, Queue<Block> movedQueue) {
        if (movedQueue.isEmpty())
            return;
        MapTile toTile = movedQueue.poll().getTile();
        PersonNode pn = getPersonNodeByPerson(person);
        if (pn == null)
            return;

        MoveAnimation ma = new MoveAnimation(pn, toTile);
        ma.play();
        ma.statusProperty().addListener(new ChangeListener<Status>() {

            @Override
            public void changed(ObservableValue<? extends Status> observableValue,
                                Status oldValue, Status newValue) {
                if(newValue==Status.STOPPED){
                    if (movedQueue.isEmpty())
                        return;
                    ma.changeDestination(movedQueue.poll().getTile());
                    ma.play();
                }
            }
        });
    }

    public static void addBuilding(Building building) {
        BuildingNode bn = new BuildingNode(building);
        bn.setLayoutX(building.getBlock().getTile().getLayoutX());
        bn.setLayoutY(building.getBlock().getTile().getLayoutY() -  bn.getHeight()/2);
        allBuildings.getChildren().add(bn);
    }

}

