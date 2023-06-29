package view;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
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

import java.net.URL;
import java.util.Objects;

public class SignUpMenuGUI extends Application {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label messageLabel;
    //level 0
    @FXML
    private TextField captchaField;
    @FXML
    private Hyperlink resetCaptchaHyperLink;
    @FXML
    private ImageView captchaImageViewer;
    //level 1
    @FXML
    private TextField username;
    @FXML
    private Label userLabel;
    //level 2
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private CheckBox randomPassCheck;
    //level 3
    @FXML
    private TextField nickname;
    //level 4
    @FXML
    private TextField email;
    //level 5
    @FXML
    private TextArea slogan;
    @FXML
    private CheckBox randomSloganCheck;
    //level 6
    @FXML
    private Button signButton;
    @FXML
    private Button nextButton;
    private static String captcha;
    private static int signUpLevel = 0;


    @FXML
    public void initialize() {
        //hide every object
        visibilityUpdate();
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
        listenerUpdate();


    }

    private void listenerUpdate(){
        switch (signUpLevel){
            case 0:
                captchaField.textProperty().addListener((observable, oldText, newText) -> {
                    if (!newText.equals(captcha)){
                        messageLabel.setText("The captcha is invalid!");
                        nextButton.setVisible(false);
                    }
                    else{
                        messageLabel.setText(null);
                        nextButton.setVisible(true);
                    }
                });
                break;
            case 1:
                username.textProperty().addListener((observable, oldText, newText) -> {
                    if (Database.getUserByUsername(newText) != null){
                        messageLabel.setText("This Username already exist!");
                        nextButton.setVisible(false);
                    }
                    else if (newText.equals("")) {
                        messageLabel.setText("Username field is empty!");
                        nextButton.setVisible(false);
                    }
                    else if (CheckFunctions.checkUsernameFormat(newText)){
                        messageLabel.setText("This Username format is invalid!");
                        nextButton.setVisible(false);
                    }
                    else{
                        messageLabel.setText(null);
                        nextButton.setVisible(true);
                    }


                });
                break;
            case 2:
                password.textProperty().addListener((observable, oldText, newText) -> {
                    if(newText.length() < 6 && !randomPassCheck.isSelected()){
                        messageLabel.setText("Your Password must be more than 5 characters!");
                        nextButton.setVisible(false);
                    }
                    else if (newText.equals("")) {
                        messageLabel.setText("Password field is empty!");
                        nextButton.setVisible(false);
                    }
                    else if(CheckFunctions.checkPasswordFormat(newText) && !randomPassCheck.isSelected()){
                        messageLabel.setText("This Password format is invalid!");
                        nextButton.setVisible(false);
                    }
                    else{
                        messageLabel.setText(null);
                        nextButton.setVisible(true);
                    }

                });
                confirmPassword.textProperty().addListener((observable, oldText, newText) -> {
                    if(password.getText().isEmpty() && !randomPassCheck.isSelected()) {
                        messageLabel.setText("The Password field is empty!");
                    }
                    else if (newText.equals("")) {
                        messageLabel.setText("Confirm Password field is empty!");
                        nextButton.setVisible(false);
                    }
                    else{
                        if (!newText.equals(password.getText()) && !randomPassCheck.isSelected())
                            messageLabel.setText("The given passwords don't match!");
                        else
                            messageLabel.setText(null);
                    }
                });
                break;
            case 3:
                nickname.textProperty().addListener((observable, oldText, newText) -> {
                    if (nickname.getText().isEmpty()){
                        messageLabel.setText("The Nickname is empty!");
                        nextButton.setVisible(false);
                    }
                    else if (newText.equals("")) {
                        messageLabel.setText("Nickname field is empty!");
                        nextButton.setVisible(false);
                    }
                    else{
                        messageLabel.setText(null);
                        nextButton.setVisible(true);
                    }
                });
                break;
            case 4:
                email.textProperty().addListener((observable, oldText, newText) -> {
                    if (CheckFunctions.checkEmailFormat(newText)){
                        messageLabel.setText("The Email format is invalid!");
                        nextButton.setVisible(false);
                    }
                    if (CheckFunctions.checkEmailExits(newText)){
                        messageLabel.setText("This Email already exist!");
                        nextButton.setVisible(false);
                    }
                    else{
                        messageLabel.setText(null);
                        nextButton.setVisible(true);
                    }
                });
                break;
        }
    }

    private void visibilityUpdate(){
        switch (signUpLevel){
            case 0:
                captchaField.setVisible(true);
                resetCaptchaHyperLink.setVisible(true);
                captchaImageViewer.setVisible(true);
                username.setVisible(false);
                userLabel.setVisible(false);
                password.setVisible(false);
                confirmPassword.setVisible(false);
                randomPassCheck.setVisible(false);
                nickname.setVisible(false);
                email.setVisible(false);
                slogan.setVisible(false);
                randomSloganCheck.setVisible(false);
                signButton.setVisible(false);
                nextButton.setVisible(false);
                break;
            case 1:
                captchaField.setVisible(false);
                resetCaptchaHyperLink.setVisible(false);
                captchaImageViewer.setVisible(false);
                username.setVisible(true);
                userLabel.setVisible(true);
                password.setVisible(false);
                confirmPassword.setVisible(false);
                randomPassCheck.setVisible(false);
                nickname.setVisible(false);
                email.setVisible(false);
                slogan.setVisible(false);
                randomSloganCheck.setVisible(false);
                signButton.setVisible(false);
                nextButton.setVisible(false);
                break;
            case 2:
                captchaField.setVisible(false);
                resetCaptchaHyperLink.setVisible(false);
                captchaImageViewer.setVisible(false);
                username.setVisible(false);
                userLabel.setVisible(false);
                password.setVisible(true);
                confirmPassword.setVisible(true);
                randomPassCheck.setVisible(true);
                nickname.setVisible(false);
                email.setVisible(false);
                slogan.setVisible(false);
                randomSloganCheck.setVisible(false);
                signButton.setVisible(false);
                nextButton.setVisible(false);
                break;
            case 3:
                captchaField.setVisible(false);
                resetCaptchaHyperLink.setVisible(false);
                captchaImageViewer.setVisible(false);
                username.setVisible(false);
                userLabel.setVisible(false);
                password.setVisible(false);
                confirmPassword.setVisible(false);
                randomPassCheck.setVisible(false);
                nickname.setVisible(true);
                email.setVisible(false);
                slogan.setVisible(false);
                randomSloganCheck.setVisible(false);
                signButton.setVisible(false);
                nextButton.setVisible(false);
                break;
            case 4:
                captchaField.setVisible(false);
                resetCaptchaHyperLink.setVisible(false);
                captchaImageViewer.setVisible(false);
                username.setVisible(false);
                userLabel.setVisible(false);
                password.setVisible(false);
                confirmPassword.setVisible(false);
                randomPassCheck.setVisible(false);
                nickname.setVisible(false);
                email.setVisible(true);
                slogan.setVisible(false);
                randomSloganCheck.setVisible(false);
                signButton.setVisible(false);
                nextButton.setVisible(false);
                break;
            case 5:
                captchaField.setVisible(false);
                resetCaptchaHyperLink.setVisible(false);
                captchaImageViewer.setVisible(false);
                username.setVisible(false);
                userLabel.setVisible(false);
                password.setVisible(false);
                confirmPassword.setVisible(false);
                randomPassCheck.setVisible(false);
                nickname.setVisible(false);
                email.setVisible(false);
                slogan.setVisible(true);
                randomSloganCheck.setVisible(true);
                signButton.setVisible(true);
                nextButton.setVisible(false);
                break;
        }
    }
    public void randomSloganSetter() {
        slogan.setVisible(!randomSloganCheck.isSelected());
    }

    public void randomPassSetter() {
        password.setVisible(!randomPassCheck.isSelected());
        confirmPassword.setVisible(!randomPassCheck.isSelected());
        password.setText("");
        confirmPassword.setText("");
    }

    public void back() {
        signUpLevel--;
        if(signUpLevel == -1){
            signUpLevel++;
            //todo go back to signup menu
        }
        visibilityUpdate();
        listenerUpdate();
    }

    public void nextStep() {
        signUpLevel++;
        visibilityUpdate();
        listenerUpdate();
        nextButton.setVisible(false);
    }

    public void resetCaptcha() {
        captcha = RandomCaptcha.generateString();
        Image captchaImage = SwingFXUtils.toFXImage(RandomCaptcha.generateImage(captcha), null);
        captchaImageViewer.setImage(captchaImage);
        listenerUpdate();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenuGUI.class.getResource("/fxml/signup-menu.fxml");
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
