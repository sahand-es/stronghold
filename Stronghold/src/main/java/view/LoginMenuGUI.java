package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Database;

import java.net.URL;

public class LoginMenuGUI extends Application {


    @FXML
    private AnchorPane salam;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        Image image = new Image(
                MarketViewController.class.getResource("/images/backgrounds/login-menu-background.jpg").toExternalForm()
        );
        BackgroundImage backgroundFill = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background background = new Background(backgroundFill);
        salam.setBackground(background);
        username.textProperty().addListener((observable, oldText, newText) -> {
            if (Database.getUserByUsername(newText) == null) {
                messageLabel.setText("Username doesn't exist!");
            }
            else{
                if(password.getText().isEmpty()){
                    messageLabel.setText("Fill password field!");
                }
                else
                    messageLabel.setText(null);
            }
        });
        password.textProperty().addListener((observable, oldText, newText) -> {
            if(username.getText().isEmpty()){
                messageLabel.setText("Fill username field!");
            }
            else if (Database.getUserByUsername(username.getText()) == null) {
                messageLabel.setText("Username doesn't exist!");
            }
            else
                messageLabel.setText(null);
        });
    }

    public void login(MouseEvent mouseEvent) {
    }

    public void signUp(MouseEvent mouseEvent) {
    }

    public void forgotPassword(MouseEvent mouseEvent) {
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenuGUI.class.getResource("/fxml/login-menu.fxml");
        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane,800,400);
        primaryStage.setScene(scene);

        //setBackground(primaryStage);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
