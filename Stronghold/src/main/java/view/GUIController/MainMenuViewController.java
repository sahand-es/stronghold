package view.GUIController;

import controller.GameControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.App;
import model.Database;
import model.Game;
import model.chat.ChatMenuGUI;
import model.map.Map;
import utility.DataManager;


public class MainMenuViewController extends Application {

    private BorderPane borderPane;
    private int width;
    private int height;

    private Label sizeLabel;
    private TextField sizeTextField;
    private Label sizeMessage;

    private Label numberOfPlayersLabel;
    private TextField numberOfPlayersTextField;
    private Label numberOfPlayersMessage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        width = (int) Screen.getPrimary().getBounds().getWidth();
        height = (int) Screen.getPrimary().getBounds().getHeight();


        borderPane = new BorderPane();
        borderPane.setPrefSize(width, height);

        setBackground();

        mainMenuPage();

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void setBackground() {
        Image image = new Image(
                MainMenuViewController.class.getResource("/images/backgrounds/main-menu-background.jpg").toExternalForm()
        );

        BackgroundImage backgroundFill = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        );

        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);
    }

    private void mainMenuPage() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Label l = new Label();
        l.setPrefHeight(180);
        vBox.getChildren().add(l);


        Button startNewGameButton = new Button("Start New Game");
        startNewGameButton.setPrefSize(500, 80);
        startNewGameButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        startNewGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    new LobbyGUI().start(App.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        vBox.getChildren().add(startNewGameButton);

        Button showMapsButton = new Button("Show Maps");
        showMapsButton.setPrefSize(500, 80);
        showMapsButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        showMapsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new ChatMenuGUI().start(App.stage);
            }
        });

        vBox.getChildren().add(showMapsButton);


        Button profileMenuButton = new Button("Profile Menu");
        profileMenuButton.setPrefSize(500, 80);
        profileMenuButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        profileMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    new ProfileMenuGUI().start(App.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        vBox.getChildren().add(profileMenuButton);


        Button logoutButton = new Button("Logout");
        logoutButton.setPrefSize(500, 80);
        logoutButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    DataManager.saveLoggedIn(null);
                    new LoginMenuGUI().start(App.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        vBox.getChildren().add(logoutButton);


        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(500, 80);
        exitButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        vBox.getChildren().add(exitButton);


        borderPane.setCenter(vBox);
    }

    private void startNewGamePage() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        Label l = new Label();
        l.setPrefHeight(100);
        vBox.getChildren().add(l);


        sizeLabel = new Label("             size: ");
        sizeLabel.setStyle("-fx-font: 25 sys");

        sizeTextField = new TextField();
        sizeTextField.setStyle("-fx-background-color: #909090; -fx-font: 20 sys");

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(sizeLabel, sizeTextField);

        sizeMessage = new Label();
        sizeMessage.setStyle("-fx-font: 15 sys; -fx-text-fill: #ff0000");

        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(hBox1, sizeMessage);

        vBox.getChildren().add(vBox1);

        numberOfPlayersLabel = new Label("number of players: ");
        numberOfPlayersLabel.setStyle("-fx-font: 25 sys");

        numberOfPlayersTextField = new TextField();
        numberOfPlayersTextField.setStyle("-fx-background-color: #909090;-fx-font: 20 sys");

        hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(numberOfPlayersLabel, numberOfPlayersTextField);

        numberOfPlayersMessage = new Label();
        numberOfPlayersMessage.setStyle("-fx-font: 15 sys; -fx-text-fill: #ff0000");

        vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(hBox1, numberOfPlayersMessage);

        vBox.getChildren().add(vBox1);


        l = new Label();
        l.setPrefHeight(100);
        vBox.getChildren().add(l);

        Button startButton = new Button("Start The Game");
        startButton.setPrefSize(400, 70);
        startButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                checkStartGame();
            }
        });

        vBox.getChildren().add(startButton);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        backButton.setPrefSize(400, 70);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainMenuPage();
            }
        });

        vBox.getChildren().add(backButton);

        borderPane.setCenter(vBox);
    }

    private void checkStartGame() {
        int s, n;


        String text = sizeTextField.getText();

        if (text == null || text.equals("")) {
            sizeMessage.setText("you must enter the size");
            return;
        }

        if (!text.matches("\\d+")) {
            sizeMessage.setText("size must be an integer");
            return;
        }

        s = Integer.parseInt(text);
        sizeMessage.setText("");

        text = numberOfPlayersTextField.getText();

        if (text == null || text.equals("")) {
            numberOfPlayersMessage.setText("you must enter the number of players");
            return;
        }

        if (!text.matches("\\d+")) {
            numberOfPlayersMessage.setText("number of players must be an integer");
            return;
        }

        n = Integer.parseInt(text);

        if (n > 8) {
            numberOfPlayersMessage.setText("number of players must be 8 or less");
        }

        numberOfPlayersMessage.setText("");

        Map map = new Map(s, s);
        Game game = new Game(map, n);

        Database.setCurrentMap(map);
        Database.setCurrentGame(game);
        GameControl.setGame(game);

        try {
            new GameMenuGUI().start(App.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
