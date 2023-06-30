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
import model.Database;
import model.User;
import utility.DataManager;

public class StartViewController extends Application {
    private BorderPane borderPane;

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

        Label label1 = new Label();
        label1.setAlignment(Pos.CENTER);
        label1.setStyle("-fx-font: 50 sys;-fx-text-fill: #ffffff");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label1);
        borderPane.setBottom(vBox);
        label1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                User user = DataManager.loadLoggedInUser();

                if (user == null){
                    try {
                        new LoginMenuGUI().start(Database.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Database.setCurrentUser(user);
                    try {
                        new MainMenuViewController().start(Database.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });



        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(2000));
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setNode(label);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label1.setText("Press any key to start the game");
                label1.requestFocus();
            }
        });
        fadeTransition.play();


        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        Database.stage = stage;
        stage.show();
    }


}
