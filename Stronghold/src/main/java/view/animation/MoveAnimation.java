package view.animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.map.Block;
import model.map.MapTile;
import model.units.Person;
import view.GameViewController;
import view.shape.PersonNode;

import java.util.Queue;

public class MoveAnimation extends Transition {
    PersonNode personNode;
    MapTile toTile;

    Queue<Block> movedQueue;

    double delX;
    double delY;

    double speed;
    public MoveAnimation(PersonNode personNode, MapTile toTile) {
        this.personNode = personNode;
        this.toTile = toTile;

        delX = GameViewController.getLayoutXForPerson(toTile) - personNode.getLayoutX();
        delY = GameViewController.getLayoutYForPerson(toTile) - personNode.getLayoutY();

        this.setCycleDuration(Duration.millis(6000));
        speed = personNode.getPerson().getSpeed() * 0.025;
    }

    public void changeDestination(MapTile mapTile) {
        toTile = mapTile;
        delX = GameViewController.getLayoutXForPerson(toTile) - personNode.getLayoutX();
        delY = GameViewController.getLayoutYForPerson(toTile) - personNode.getLayoutY();
    }

    @Override
    protected void interpolate(double v) {
        double scale = Math.abs(delY/delX);
        double changeInX = delX < 0 ? -speed : +speed;
        double changeInY = delY < 0 ? -speed * scale : +speed * scale;
        if (Math.abs(delX) >= 1) {
            delX -= changeInX;
            personNode.setLayoutX(personNode.getLayoutX() + changeInX);
        }
        if (Math.abs(delY) >= 1) {
            delY -= changeInY;
            personNode.setLayoutY(personNode.getLayoutY() + changeInY);
        }
        if (!(Math.abs(delY) >= 1) && !(Math.abs(delX) >= 1)) {
            this.stop();
        }
    }
}
