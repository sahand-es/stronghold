package view;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Database;
import utility.RandomCaptcha;

import java.net.URL;
import java.util.Objects;

public class ProfileMenuGUI extends Application {
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {

    }


    public void profileMenu(MouseEvent mouseEvent) {
    }

    public void avatarMenu(MouseEvent mouseEvent) {
    }

    public void scoreBoard(MouseEvent mouseEvent) {
    }

    public void goToMainMenu(MouseEvent mouseEvent) {
    }

    public void changePass(ActionEvent actionEvent) {
    }

    public void changeSlogan(ActionEvent actionEvent) {
    }

    public void save(ActionEvent actionEvent) {
    }

    public void resetCaptcha(ActionEvent actionEvent) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenuGUI.class.getResource("/fxml/profile-menu.fxml");
        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane,1000,600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
