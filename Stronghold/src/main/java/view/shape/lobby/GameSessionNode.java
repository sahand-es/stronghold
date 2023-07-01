package view.shape.lobby;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Game;
import utility.RandomGenerators;

public class GameSessionNode {
    private Game game;
    private HBox mainNode;
    private TextArea usersArea;
    private Label joinedNumberLabel;
    private Button join;
    private Button leave;
    private Button start;

    //todo constructor must get game and the user that wants the node to be made
    public GameSessionNode() {
        //this.game = game;
        mainNode  = new HBox();
        mainNode.setAlignment(Pos.CENTER);
        mainNode.setSpacing(100);
        mainNode.setPrefSize(1300,100);
        mainNode.setStyle("-fx-background-color: #263f73");

        Label idLabel = new Label("Session Id: " + RandomGenerators.randomSessionId());
        idLabel.setPrefSize(150,100);
        idLabel.setStyle("-fx-font: 18 sys");
        idLabel.setTextFill(Color.WHITE);
        mainNode.getChildren().add(idLabel);

        usersArea = new TextArea();
        usersArea.setEditable(false);
        usersArea.setPrefSize(300,100);
        usersArea.setStyle("-fx-font: 20 sys");
        //todo get usernames from game(from constructor)
        usersArea.setText("Test mikonim \nbebinim\nche\nkhabare\nshayad\njaleb shod yeho khoshemoon oomad");
        mainNode.getChildren().add(usersArea);

        //todo set players count by game(from constructor)
        joinedNumberLabel = new Label("Players count: 1/5");
        joinedNumberLabel.setPrefSize(150,70);
        joinedNumberLabel.setStyle("-fx-font: 18 sys");
        joinedNumberLabel.setTextFill(Color.WHITE);
        mainNode.getChildren().add(joinedNumberLabel);


        //todo it must be checked if the user is joined in this match or not to set the button visibility
        join = new Button("Join");
        join.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(join);

        //todo it must be checked if the user is joined in this match or not to set the button visibility
        leave = new Button("Leave");
        leave.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(leave);

        //todo it must be checked if the user is joined in this match or not to set the button visibility
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

    public Game getGame() {
        return game;
    }
}
