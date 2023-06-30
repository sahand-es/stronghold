package view.GUIController;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.App;
import model.Database;
import model.User;
import utility.DataManager;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class StartViewController extends Application {
    private BorderPane borderPane;
    private Label label1;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();
        borderPane = new BorderPane();
        borderPane.setPrefSize(width,height);
        borderPane.setStyle("-fx-background-color: #000000");

        Label label = new Label("StrongHold");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font: 150 sys;-fx-text-fill: #ffffff");
        borderPane.setCenter(label);

        label1 = new Label();
        label1.setAlignment(Pos.CENTER);
        label1.setStyle("-fx-font: 50 sys;-fx-text-fill: #ffffff");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label1);
        borderPane.setBottom(vBox);




        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(2000));
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setNode(label);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                connect("localhost",8000);
            }
        });
        fadeTransition.play();


        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        App.stage = stage;
        stage.show();
    }

    private void connect(String host,int port){
        try {
            Socket socket = new Socket(host,port);
            App.setSocket(socket);
            App.writeToServer("hello");
            System.out.println(App.readFromServer());
            label1.setText("Press any key to start the game");
            label1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    User user = DataManager.loadLoggedInUser();

                    try {
                        if (user == null){
                            new LoginMenuGUI().start(App.stage);
                        } else {
                            Database.setCurrentUser(user);
                            new MainMenuViewController().start(App.stage);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            label1.requestFocus();

        } catch (ConnectException e) {
            label1.setText("No server found!, press eny key to reconnect");
            label1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    label1.setText("Trying to reconnect ...");
                    FadeTransition fadeTransition = new FadeTransition();
                    fadeTransition.setDuration(Duration.millis(2000));
                    fadeTransition.setFromValue(1.0);
                    fadeTransition.setToValue(1.0);
                    fadeTransition.setNode(label1);
                    fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            connect(host,port);
                        }
                    });
                    fadeTransition.play();
                }
            });
            label1.requestFocus();

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
