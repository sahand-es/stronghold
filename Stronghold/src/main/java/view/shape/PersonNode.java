package view.shape;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.units.Person;
import model.units.Soldier;
import utility.DataManager;
import view.GameViewController;

import java.io.IOException;


public class PersonNode extends Rectangle {
    private final Person person;
    private VBox detailBox;
    private ProgressBar pb;

    public PersonNode(Person person) {
        super(20, 25);
        this.person = person;
        this.setFill(new ImagePattern(new Image(person.getUnitName().getImagePath())));

        setUpDownAnimation();

        try {
            detailBox = FXMLLoader.load((this.getClass().getResource(DataManager.SOLDIER_DETAIL_BOX)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setDetailBox();

        PersonNode personNode = this;
        personNode.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    showDetailsBox();
                }
            }
        });

        personNode.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameViewController.removeNode(detailBox);
            }
        });
    }

    private void setDetailBox() {
        pb = (ProgressBar) detailBox.getChildren().get(0);
        Text kind = (Text) detailBox.getChildren().get(1);
        kind.setText(person.getUnitName().getName());
        Text speed = (Text) detailBox.getChildren().get(2);
        speed.setText("Speed:    " + person.getSpeed());
        Text damage = (Text) detailBox.getChildren().get(3);
        HBox hBox = (HBox) detailBox.getChildren().get(4);
        ImageView soldierState = (ImageView) hBox.getChildren().get(0);

        if (person instanceof Soldier){
             Soldier soldier = (Soldier) person;
            damage.setText("Damage:     " + soldier.getDamage());
            soldierState.setImage(new Image(soldier.getSoldierUnitState().getImagePath()));
        }

        pb.setProgress(1);
    }

    private void showDetailsBox() {
        pb.setProgress((double) person.getHp() / 100);

        GameViewController.addNode(detailBox);
    }


    private void setUpDownAnimation() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), this);
        tt.setByY(0.1f);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);

        tt.play();
    }

    public Person getPerson() {
        return person;
    }
}
