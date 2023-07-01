package view.GUIController;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.App;
import model.Database;
import model.User;
import utility.DataManager;
import utility.RandomCaptcha;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
            if (getUserFromServer(newText) == null) {
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
            else if (getUserFromServer(username.getText()) == null) {
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
            else if (getUserFromServer(username.getText()) == null) {
                messageLabel.setText("Username doesn't exist!");
            }
            else if(password.getText().isEmpty()){
                messageLabel.setText("Fill password field!");
            }
            else
                messageLabel.setText(null);
        });
    }

    public void login() throws IOException {
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
        else if (!Objects.requireNonNull(getUserFromServer(username.getText())).checkPassword(
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
            User user = getUserFromServer(username.getText());
            App.setCurrentUser(user);
            HashMap<String,String> data = new HashMap<>();
            data.put("menu","login");
            data.put("command","setUser");
            data.put("user",user.getUsername());
            String dataStr = new Gson().toJson(data);
            App.writeToServer(dataStr);

            if(stayLoggedCheckBox.isSelected()){
                DataManager.saveLoggedIn(Database.getCurrentUser());
            }

            try {
                new MainMenuViewController().start(App.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void signUp() {
        try {
            new SignUpMenuGUI().start(App.stage);
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

    private User getUserFromServer(String username){
        HashMap<String,String> data = new HashMap<>();
        data.put("menu","login");
        data.put("command","getUser");
        data.put("user",username);
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
            dataStr = App.readFromServer();
            User user = new Gson().fromJson(dataStr,User.class);
            return user;
        } catch (Exception e){
            return null;
        }
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
