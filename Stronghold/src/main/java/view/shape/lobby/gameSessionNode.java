package view.shape.lobby;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Game;
import model.User;
import utility.RandomGenerators;

import java.awt.geom.Area;

public class gameSessionNode {
    private Game game;
    private HBox mainNode;
    private TextArea usersArea;
    private Label joinedNumberLabel;
    private Button join;
    private Button leave;
    private Button start;

    public gameSessionNode(Game game) {
        this.game = game;
        mainNode  = new HBox();
        mainNode.setAlignment(Pos.CENTER);
        mainNode.setSpacing(100);
        mainNode.setPrefSize(600,70);
        mainNode.setStyle("-fx-background-color: #263f73");

        Label idLabel = new Label("Session Id: " + RandomGenerators.randomSessionId());
        joinedNumberLabel.setPrefSize(100,70);
        idLabel.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(idLabel);

        usersArea = new TextArea();
        usersArea.setEditable(false);
        usersArea.setPrefSize(200,70);
        usersArea.setStyle("-fx-font: 20 sys");
        //todo get usernames from game
        usersArea.setText("Test mikonim \nbebinim\nche\nkhabare\nshayad\njaleb shod yeho khoshemoon oomad");
        mainNode.getChildren().add(usersArea);

        //todo set count by game
        joinedNumberLabel = new Label("Players count: 1/5");
        joinedNumberLabel.setPrefSize(100,70);
        joinedNumberLabel.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(joinedNumberLabel);


        join = new Button("Join");
        join.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(join);


        leave = new Button("Leave");
        leave.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(leave);

        start = new Button("Start");
        start.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(start);
    }

    public HBox getMainNode() {
        return mainNode;
    }

    public TextArea getUsersArea() {
        return usersArea;
    }

    public Label getJoinedNumberLabel() {
        return joinedNumberLabel;
    }

    public Button getJoin() {
        return join;
    }

    public Button getLeave() {
        return leave;
    }

    public Button getStart() {
        return start;
    }
}
