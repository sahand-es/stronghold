package view;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Database;
import utility.CheckFunctions;
import utility.RandomCaptcha;
import view.enums.messages.SignUpMessages;

import java.net.URL;
import java.util.Objects;

public class SignUpMenuGUI extends Application {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField username;
    @FXML
    private TextField nickname;
    @FXML
    private TextArea slogan;
    @FXML
    private TextField email;
    @FXML
    private TextField captchaField;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label userLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private ImageView captchaImageViewer;
    private static String captcha;


    @FXML
    public void initialize() {
        //load background
        Image image = new Image(
                Objects.requireNonNull(MarketViewController.class.getResource(
                        "/images/backgrounds/login-menu-background.jpg")).toExternalForm()
        );
        BackgroundImage backgroundFill = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);

        //load captcha
        captcha = RandomCaptcha.generateString();
        Image captchaImage = SwingFXUtils.toFXImage(RandomCaptcha.generateImage(captcha), null);
        captchaImageViewer.setImage(captchaImage);

        //add listeners
        username.textProperty().addListener((observable, oldText, newText) -> {
            if (Database.getUserByUsername(newText) != null)
                userLabel.setText("This Username already exist!");
            else if (CheckFunctions.checkUsernameFormat(newText))
                userLabel.setText("This Username format is invalid!");
            else
                userLabel.setText(null);

        });
        password.textProperty().addListener((observable, oldText, newText) -> {
            if(newText.length() < 6)
                passwordLabel.setText("Your Password must be more than 5 characters!");
            else if(CheckFunctions.checkPasswordFormat(newText))
                passwordLabel.setText("This Password format is invalid!");
            else
                passwordLabel.setText(null);
        });
        confirmPassword.textProperty().addListener((observable, oldText, newText) -> {
            if(password.getText().isEmpty()) {
                passwordLabel.setText("The Password field is empty!");
            }
            else{
                if (!newText.equals(password.getText()))
                    passwordLabel.setText("The given passwords don't match!");
                else
                    passwordLabel.setText(null);
            }
        });
        email.textProperty().addListener((observable, oldText, newText) -> {
            if (CheckFunctions.checkEmailFormat(newText)){
                emailLabel.setText("The Email format is invalid!");
            }
            if (CheckFunctions.checkEmailExits(newText)){
                emailLabel.setText("This Email already exist!");
            }
            else
                emailLabel.setText(null);
        });

    }

    public void randomSloganSetter(ActionEvent actionEvent) {
    }

    public void randomPassSetter(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void signup(ActionEvent actionEvent) {
    }

    public void resetCaptcha(ActionEvent actionEvent) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenuGUI.class.getResource("/fxml/signup-menu.fxml");
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
