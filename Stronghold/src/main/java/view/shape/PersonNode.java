package view.shape;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.units.Person;


public class PersonNode extends Rectangle {
    Person person;

    public PersonNode(Person person) {
        super(35, 45);
        this.person = person;
        this.setFill(new ImagePattern(new Image(person.getName().getImagePath())));
    }
}
