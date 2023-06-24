package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class MainMenuViewController extends Application {

    private BorderPane borderPane;
    private int width;
    private int height;

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

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Label l = new Label();
        l.setPrefHeight(180);
        vBox.getChildren().add(l);


        Button startNewGameButton = new Button("Start New Game");
        startNewGameButton.setPrefSize(500,80);
        startNewGameButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        startNewGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
            }
        });

        vBox.getChildren().add(startNewGameButton);

        Button showMapsButton = new Button("Show Maps");
        showMapsButton.setPrefSize(500,80);
        showMapsButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        showMapsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
            }
        });

        vBox.getChildren().add(showMapsButton);


        Button profileMenuButton = new Button("Profile Menu");
        profileMenuButton.setPrefSize(500,80);
        profileMenuButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        profileMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
            }
        });

        vBox.getChildren().add(profileMenuButton);


        Button logoutButton = new Button("Logout");
        logoutButton.setPrefSize(500,80);
        logoutButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
            }
        });

        vBox.getChildren().add(logoutButton);


        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(500,80);
        exitButton.setStyle("-fx-font: 25 sys ;-fx-background-color: #808080 ; -fx-border-color: #262115");
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        vBox.getChildren().add(exitButton);


        borderPane.setCenter(vBox);



        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }

    private void setBackground(){
        Image image = new Image(
                MainMenuViewController.class.getResource("/images/backgrounds/main-menu-background.jpg").toExternalForm()
        );




        BackgroundImage backgroundFill = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        );

        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);
    }
}
