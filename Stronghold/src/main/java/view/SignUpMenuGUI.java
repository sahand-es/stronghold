package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

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
    private Label nicknameLabel;
    @FXML
    private ImageView captchaImageViewer;
    @FXML
    private CheckBox stayLoggedCheckBox;
    private static String captcha;

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

    public void back(ActionEvent actionEvent) {
    }

    public void signup(ActionEvent actionEvent) {
    }

    public void resetCaptcha(ActionEvent actionEvent) {
    }
}
