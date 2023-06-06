package view.shape;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.units.Person;


public class PersonNode extends Rectangle {
    private final Person person;

    public PersonNode(Person person) {
        super(25, 30);
        this.person = person;
        this.setFill(new ImagePattern(new Image(person.getName().getImagePath())));

        setUpDownAnimation();
    }

    private void setUpDownAnimation() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), this);
        tt.setByY(0.2);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);

        tt.play();
    }

    public Person getPerson() {
        return person;
    }
}
