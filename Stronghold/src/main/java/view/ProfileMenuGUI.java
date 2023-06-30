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
    private Button passChangeButton;
    @FXML
    private Button sloganChangeButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField userLabel;
    @FXML
    private TextField nickNameLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private TextArea sloganLabel;
    @FXML
    private TextArea newSloganField;
    @FXML
    private PasswordField oldPass;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private TextField captchaField;
    @FXML
    private ImageView captchaView;
    @FXML
    private ImageView avatarView;
    @FXML
    private Hyperlink resetHyper;

    private String captcha;


    @FXML
    public void initialize() {
        //load backGround image
        Image image = new Image(
                Objects.requireNonNull(MarketViewController.class.getResource(
                        "/images/backgrounds/profile-menu-background.jpg")).toExternalForm()
        );
        BackgroundImage backgroundFill = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        Background background = new Background(backgroundFill);
        anchorPane.setBackground(background);

        saveButton.setVisible(false);
        oldPass.setVisible(false);
        newPass.setVisible(false);
        confirmPass.setVisible(false);
        newSloganField.setVisible(false);
        captchaView.setVisible(false);
        captchaField.setVisible(false);
        resetHyper.setVisible(false);



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
