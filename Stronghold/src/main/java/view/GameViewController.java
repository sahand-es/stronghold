package view;

import javafx.animation.Animation;
import javafx.animation.Animation.*;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.map.Block;
import model.map.MapPane;
import model.map.MapTile;
import model.units.Person;
import view.animation.MoveAnimation;
import view.shape.PersonNode;

import java.util.Queue;

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


    private static PathTransition newPathTransitionTo(PersonNode personNode, double toX, double toY, Queue<Block> movedQueue) {
        final MapTile[] toBlock = {movedQueue.poll().getTile()};

        double fromX = personNode.getLayoutBounds().getWidth() / 2;
        double fromY = personNode.getLayoutBounds().getHeight() / 2;

        double finalToX = toX - personNode.getLayoutBounds().getWidth();
        double finalToY = toY - personNode.getLayoutBounds().getHeight() / 2;

        toX -= personNode.getLayoutX() - personNode.getLayoutBounds().getWidth() / 2;
        toY -= personNode.getLayoutY() - personNode.getLayoutBounds().getHeight() / 2;


        Path path = new Path();
        path.getElements().add(new MoveTo(fromX, fromY));
        path.getElements().add(new LineTo(toX, toY));


        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setNode(personNode);
        transition.setDuration(Duration.seconds(2));

 transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                personNode.setLayoutX(finalToX);
                personNode.setLayoutX(finalToY);
                if (movedQueue.isEmpty())
                    return;
                else {
                    toBlock[0] = movedQueue.poll().getTile();
                    PathTransition pt = newPathTransitionTo(personNode,
                            GameViewController.getLayoutXForPerson(toBlock[0]),
                            GameViewController.getLayoutYForPerson(toBlock[0]), movedQueue);
                    pt.play();
                }
            }
        });

        return transition;
    }
}

