package view.GUIController;

import com.google.gson.Gson;
import controller.GameControl;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import model.chat.ChatMenuGUI;
import model.map.Map;
import utility.RandomGenerators;
import view.MainMenu;
import view.shape.lobby.GameSessionNode;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LobbyGUI extends Application {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox vBox;
    @FXML
    private ChoiceBox playerNumberChoice;

    private ArrayList<HBox> gameSessionMainNodes = new ArrayList<>();

    private ArrayList<Session> sessions = new ArrayList<>();

    private Session gameSession;

    private User currentUser = App.getCurrentUser();


    private Timeline timeline;


    ObservableList<String> numberList = FXCollections.observableArrayList("2","3","4","5","6","7","8");

    @FXML
    public void initialize() {

        playerNumberChoice.setItems(numberList);
        playerNumberChoice.setValue(numberList.get(0));

        //load background
        Image image = new Image(
                Objects.requireNonNull(MarketViewController.class.getResource(
                        "/images/backgrounds/login-menu-background.jpg")).toExternalForm()
        );
        BackgroundImage backgroundFill = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background background = new Background(backgroundFill);
        anchorPane.setBackground(background);


        timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {refresh();}));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void createGame() {
        for (Session session:sessions) {
            if (session.getUsers().contains(currentUser.getUsername())){
                //todo give error
                return;
            }
        }

        HashMap<String,String> data = new HashMap<>();
        data.put("command","createGame");
        data.put("id",RandomGenerators.randomSessionId());
        data.put("numberOfPlayers",playerNumberChoice.getValue().toString());
        data.put("username",currentUser.getUsername());
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh() {
        //remove all the nodes from ArrayList
        for (HBox node : gameSessionMainNodes) {
            vBox.getChildren().remove(node);
        }
        gameSessionMainNodes.clear();

        sessions = getSessionsFromServer();
        //make them all over again the process of making a new node will refresh all its details
        for (Session session : sessions) {
            if (session.isStarted() && session.getUsers().contains(currentUser.getUsername())){
                //TODO start game
                timeline.stop();

                HashMap<String ,String> data = new HashMap<>();
                data.put("command","startGame");
                data.put("username",currentUser.getUsername());
                data.put("id",session.getSessionId());
                String dataStr = new Gson().toJson(data);
                try {
                    App.writeToServer(dataStr);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Map map = new Map(70, 70);
                Game game = new Game(map, session);


                System.out.println("mmm");

                Database.setCurrentMap(map);
                Database.setCurrentGame(game);
                GameControl.setGame(game);

                try {
                    new GameMenuGUI().start(App.stage);
                    return;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
            if (session.getUsers().size() != 0  && !session.isStarted()){
                HBox newSessionNode = new GameSessionNode(session, currentUser.getUsername(), sessions).getMainNode();
                gameSessionMainNodes.add(newSessionNode);
                vBox.getChildren().add(newSessionNode);
            }
        }
    }

    private ArrayList<Session> getSessionsFromServer() {
        HashMap<String,String> data = new HashMap<>();
        data.put("command","getAllSessions");
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
            dataStr = App.readFromServer();
            ArrayList<Session> out = Session.fromJsonArrayList(dataStr);
            return out;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public void back() {
        try {
            timeline.stop();
            new MainMenuViewController().start(App.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void chat() {
        timeline.stop();
        new ChatMenuGUI().start(App.stage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenuGUI.class.getResource("/fxml/lobby.fxml");
        assert url != null;
        AnchorPane aPane = FXMLLoader.load(url);
        Scene scene = new Scene(aPane,1000,600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
