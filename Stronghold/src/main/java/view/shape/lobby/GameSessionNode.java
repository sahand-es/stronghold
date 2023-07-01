package view.shape.lobby;

import com.google.gson.Gson;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.App;
import model.Session;
import utility.RandomGenerators;
import view.GUIController.LobbyGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameSessionNode {
    private Session session;
    private HBox mainNode;
    private TextArea usersArea;
    private Label joinedNumberLabel;
    private Button join;
    private Button leave;
    private Button start;
    private Label errorMessage;


    public GameSessionNode(Session session, String currentUserUsername, ArrayList<Session> sessions) {
        this.session = session;
        mainNode = new HBox();
        mainNode.setAlignment(Pos.CENTER);
        mainNode.setSpacing(50);
        mainNode.setPrefSize(1400,100);
        mainNode.setStyle("-fx-background-color: #263f73");

        Label idLabel = new Label("Session Id: " + session.getSessionId());
        idLabel.setPrefSize(150,100);
        idLabel.setStyle("-fx-font: 14 sys");
        idLabel.setTextFill(Color.WHITE);
        mainNode.getChildren().add(idLabel);

        usersArea = new TextArea();
        usersArea.setEditable(false);
        usersArea.setPrefSize(300,100);
        usersArea.setStyle("-fx-font: 14 sys");

        String usernames = session.getUsers().toString();
        usersArea.setText(usernames);
        mainNode.getChildren().add(usersArea);


        joinedNumberLabel = new Label("Players count: " + session.getUsers().size()
                + " / " + session.getNumberOfPlayers());
        joinedNumberLabel.setPrefSize(120,70);
        joinedNumberLabel.setStyle("-fx-font: 14 sys");
        joinedNumberLabel.setTextFill(Color.WHITE);
        mainNode.getChildren().add(joinedNumberLabel);


        join = new Button("Join");
        join.setStyle("-fx-font: 14 sys");
        mainNode.getChildren().add(join);
        join.setVisible(!session.getUsers().contains(currentUserUsername));

        join.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (Session s : sessions) {
                    if (s.getUsers().contains(currentUserUsername)) {
                        errorMessage.setText("You're joined in another game!");
                        return;
                    }
                }

                HashMap<String ,String> data = new HashMap<>();
                data.put("command","joinSession");
                data.put("username",currentUserUsername);
                data.put("id",session.getSessionId());
                String dataStr = new Gson().toJson(data);
                try {
                    App.writeToServer(dataStr);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                join.setVisible(false);
                leave.setVisible(true);
                start.setVisible(true);
            }
        });


        leave = new Button("Leave");
        leave.setStyle("-fx-font: 14 sys");
        mainNode.getChildren().add(leave);
        leave.setVisible(session.getUsers().contains(currentUserUsername));

        leave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HashMap<String ,String> data = new HashMap<>();
                data.put("command","leaveSession");
                data.put("username",currentUserUsername);
                data.put("id",session.getSessionId());
                String dataStr = new Gson().toJson(data);
                try {
                    App.writeToServer(dataStr);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                join.setVisible(true);
                leave.setVisible(false);
                start.setVisible(false);
            }
        });

        start = new Button("Start");
        start.setStyle("-fx-font: 14 sys");
        mainNode.getChildren().add(start);
        start.setVisible(session.getUsers().contains(currentUserUsername));
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (session.getUsers().get(0).equals(currentUserUsername)){
                    HashMap<String ,String> data = new HashMap<>();
                    data.put("command","startSession");
                    data.put("id",session.getSessionId());
                    String dataStr = new Gson().toJson(data);
                    try {
                        App.writeToServer(dataStr);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    errorMessage.setText("You're not admin");
                }
            }
        });

        errorMessage = new Label();
        errorMessage.setPrefSize(150,100);
        errorMessage.setStyle("-fx-font: 14 sys");
        errorMessage.setTextFill(Color.RED);
        mainNode.getChildren().add(errorMessage);

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
