package view.shape.profile;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.User;

public class RankNode {
    private User user;

    private HBox hBox;

    public RankNode() {


        hBox  = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);
        hBox.setPrefSize(600,70);

        Rectangle avatar = new Rectangle(50,50);
        avatar.setFill(new ImagePattern(
                new Image(RankNode.class.getResource("/images/1.png").toExternalForm())
        ));

        hBox.getChildren().add(avatar);

        Label username = new Label("Username");
        username.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(username);

        Label score = new Label("1000 / 9876");
        score.setStyle("-fx-font: 20 sys");

        hBox.getChildren().add(score);
    }

    public HBox gethBox() {
        return hBox;
    }
}
