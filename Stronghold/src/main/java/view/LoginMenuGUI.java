package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Database;
import model.User;
import utility.DataManager;
import utility.RandomCaptcha;
import java.net.URL;
import java.util.Objects;
import javafx.embed.swing.SwingFXUtils;

public class LoginMenuGUI extends Application {


    @FXML
    private AnchorPane pane;
    @FXML
    private TextField username;
    @FXML
    private TextField captchaField;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView captchaImageViewer;
    @FXML
    private CheckBox stayLoggedCheckBox;
    private static String captcha;

    @FXML
    public void initialize() {
        //load background
        Image image = new Image(
                Objects.requireNonNull(MarketViewController.class.getResource(
                        "/images/backgrounds/login-menu-background.jpg")).toExternalForm()
        );
        BackgroundImage backgroundFill = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);

        //load captcha
        captcha = RandomCaptcha.generateString();
        Image captchaImage = SwingFXUtils.toFXImage(RandomCaptcha.generateImage(captcha), null);
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
                else if(captchaField.getText().isEmpty()){
                    messageLabel.setText("Fill captcha field!");
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
            else if(captchaField.getText().isEmpty()){
                messageLabel.setText("Fill captcha field!");
            }
            else
                messageLabel.setText(null);
        });
        captchaField.textProperty().addListener((observable, oldText, newText) -> {
            if(username.getText().isEmpty()){
                messageLabel.setText("Fill username field!");
            }
            else if (Database.getUserByUsername(username.getText()) == null) {
                messageLabel.setText("Username doesn't exist!");
            }
            else if(password.getText().isEmpty()){
                messageLabel.setText("Fill password field!");
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
            resetCaptcha();
            captchaField.setText(null);
        }
        else if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Your password field is empty");
            alert.showAndWait();
            resetCaptcha();
            captchaField.setText(null);
        }
        else if (captchaField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Your captcha field is empty");
            alert.showAndWait();
            resetCaptcha();
            captchaField.setText(null);
        }
        else if (messageLabel.getText() != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("One of the fields are invalid");
            alert.showAndWait();
            resetCaptcha();
            captchaField.setText(null);
        }
        else if (!captchaField.getText().equals(captcha)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Captcha invalid");
            alert.showAndWait();
            resetCaptcha();
            captchaField.setText(null);
        }
        else if (!Objects.requireNonNull(Database.getUserByUsername(username.getText())).checkPassword(
                password.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Login Error");
            alert.setContentText("Incorrect password, naaghola!");
            alert.showAndWait();
            resetCaptcha();
            captchaField.setText(null);
        }
        else {
            User user = Database.getUserByUsername(username.getText());
            Database.setCurrentUser(user);
            if(stayLoggedCheckBox.isSelected()){
                DataManager.saveLoggedIn(Database.getCurrentUser());
            }

            try {
                new MainMenuViewController().start(Database.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void signUp() {
        try {
            new SignUpMenuGUI().start(Database.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void forgotPassword() {

    }

    public void resetCaptcha() {
        captcha = RandomCaptcha.generateString();
        Image captchaImage = SwingFXUtils.toFXImage(RandomCaptcha.generateImage(captcha), null);
        captchaImageViewer.setImage(captchaImage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenuGUI.class.getResource("/fxml/login-menu.fxml");
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
