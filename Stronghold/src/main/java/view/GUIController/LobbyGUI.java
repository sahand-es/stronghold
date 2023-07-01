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
import view.shape.lobby.GameSessionNode;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class LobbyGUI extends Application {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox VBox;
    @FXML
    private ChoiceBox playerNumberChoice;

    private ArrayList<HBox> gameSessionMainNodes = new ArrayList<>();


    ObservableList<String> numberList = FXCollections.observableArrayList("2","3","4","5","6","7","8");

    @FXML
    public void initialize() {
        //todo ongoing sessions must be added to gameSessionMainNodes ArrayList inorder to be shown in lobby

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



    }

    public void createGame() {
        //todo check whether if the user is joined in another match
        //todo make a new Game
        //todo make a new GameSession node via this line of code:

        HBox newSession = new GameSessionNode().getMainNode();
        gameSessionMainNodes.add(newSession);
        VBox.getChildren().add(newSession);

    }

    public void refresh() {
        //todo remove all the nodes from ArrayList and make them all over again the process of making a new node will refresh all its details

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
