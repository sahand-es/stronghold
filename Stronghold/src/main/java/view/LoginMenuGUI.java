package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Database;
import model.User;
import utility.RandomCaptcha;

import java.net.URL;
import java.util.Objects;

public class LoginMenuGUI extends Application {


    @FXML
    private AnchorPane pane;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView captchaImageViewer;
    @FXML
    private CheckBox stayLoggedCheckBox;
    private String captcha;

    @FXML
    public void initialize() {
        //load background
        Image image = new Image(
                MarketViewController.class.getResource(
                        "/images/backgrounds/login-menu-background.jpg").toExternalForm()
        );

        BackgroundImage backgroundFill = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);

        Background background = new Background(backgroundFill);
        pane.setBackground(background);

        //load captcha
        captcha = RandomCaptcha.generate();

        Image captchaImage = new Image(
                MarketViewController.class.getResource(
                        "/captcha/captcha.png").toExternalForm()
        );
        captchaImageViewer.setImage(captchaImage);

        //add listeners
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

    public void login() {
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Your username field is empty");
            alert.showAndWait();
        }
        else if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Your password field is empty");
            alert.showAndWait();
        }
        else if (messageLabel.getText() != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("One of the fields are invalid");
            alert.showAndWait();
        }
        else if (!Objects.requireNonNull(Database.getUserByUsername(username.getText())).checkPassword(
                password.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Incorrect password, naaghola!");
            alert.showAndWait();
        }
        //todo check captcha
        else {
            User user = Database.getUserByUsername(username.getText());
            Database.setCurrentUser(user);

            //todo go to main menu
        }
    }

    public void signUp() {
        //todo change menu to signup
    }

    public void forgotPassword() {
        //todo change menu to forgot menu
    }

    public void resetCaptcha() {
        captcha = RandomCaptcha.generate();
        Image captchaImage = new Image(
                MarketViewController.class.getResource(
                        "/captcha/captcha.png").toExternalForm()
        );
        captchaImageViewer.setImage(captchaImage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenuGUI.class.getResource("/fxml/login-menu.fxml");
        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane,1000,600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
