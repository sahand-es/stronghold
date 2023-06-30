package view.shape.profile;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.User;


public class RankNode {
    private User user;

    private HBox hBox;

    public RankNode(User user) {
        this.user = user;

        hBox  = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);
        hBox.setPrefSize(600,70);
        hBox.setStyle("-fx-background-color: #263f73");

        Rectangle avatar = new Rectangle(50,50);
        avatar.setFill(new ImagePattern(
                new Image(RankNode.class.getResource(user.getAvatarPath()).toExternalForm())
        ));

        hBox.getChildren().add(avatar);

        Label username = new Label(user.getUsername());
        username.setStyle("-fx-font: 20 sys");
        username.setTextFill(Color.WHITE);

        hBox.getChildren().add(username);

        Label score = new Label("HighScore: " + user.getHighScore());
        score.setStyle("-fx-font: 20 sys");
        score.setTextFill(Color.WHITE);

        hBox.getChildren().add(score);
    }

    public HBox gethBox() {
        return hBox;
    }

}
