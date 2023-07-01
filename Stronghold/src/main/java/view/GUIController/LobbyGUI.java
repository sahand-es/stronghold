package view.GUIController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Database;
import model.Session;
import model.User;
import utility.RandomGenerators;
import view.shape.lobby.GameSessionNode;

import java.net.DatagramPacket;
import java.net.URL;
import java.util.ArrayList;
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

    //todo check with network for current User
    private User currentUser = Database.getUserByUsername("Sousef");


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


        refresh();
    }

    public void createGame() {
        for (Session session:sessions) {
            if (session.getUsers().contains(currentUser.getUsername())){
                System.out.println("kir");
                //todo give error
                return;
            }
        }

        Session session = new Session(RandomGenerators.randomSessionId(),
                Integer.parseInt(playerNumberChoice.getValue().toString()), currentUser.getUsername());

        //todo used sessions
        sessions.add(session);
        refresh();

    }

    public void refresh() {
        //remove all the nodes from ArrayList
        for (HBox node : gameSessionMainNodes) {
            vBox.getChildren().remove(node);
        }
        gameSessionMainNodes.clear();

        //todo used sessions
        //make them all over again the process of making a new node will refresh all its details
        for (Session session : sessions) {
            if (session.getUsers().size() != 0 &&
                    session.getUsers().size() < session.getNumberOfPlayers() && !session.isStarted()){
                HBox newSessionNode = new GameSessionNode(session, currentUser.getUsername()).getMainNode();
                gameSessionMainNodes.add(newSessionNode);
                vBox.getChildren().add(newSessionNode);
            }
        }

    }

    public void back() {
        //todo go to previous menu
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
