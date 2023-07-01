package view.shape.lobby;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Session;
import utility.RandomGenerators;

public class GameSessionNode {
    private Session session;
    private HBox mainNode;
    private TextArea usersArea;
    private Label joinedNumberLabel;
    private Button join;
    private Button leave;
    private Button start;
    private Label errorMessage;


    public GameSessionNode(Session session, String currentUserUsername) {
        this.session = session;
        mainNode = new HBox();
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

        String usernames = session.getUsers().toString();
        usersArea.setText(usernames);
        mainNode.getChildren().add(usersArea);


        joinedNumberLabel = new Label("Players count: " + session.getUsers().size()
                + " / " + session.getNumberOfPlayers());
        joinedNumberLabel.setPrefSize(150,70);
        joinedNumberLabel.setStyle("-fx-font: 18 sys");
        joinedNumberLabel.setTextFill(Color.WHITE);
        mainNode.getChildren().add(joinedNumberLabel);


        join = new Button("Join");
        join.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(join);
        join.setVisible(!session.getUsers().contains(currentUserUsername));


        leave = new Button("Leave");
        leave.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(leave);
        leave.setVisible(session.getUsers().contains(currentUserUsername));


        start = new Button("Start");
        start.setStyle("-fx-font: 20 sys");
        mainNode.getChildren().add(start);
        start.setVisible(session.getUsers().contains(currentUserUsername));

        errorMessage = new Label();
        errorMessage.setStyle("-fx-font: 20 sys");
        errorMessage.setTextFill(Color.RED);
        
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

    public Session getSession() {
        return session;
    }
}
