package view;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Database;
import model.User;
import utility.CheckFunctions;
import utility.RandomCaptcha;
import view.shape.profile.RankScroll;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @FXML
    private Label messageLabel;
    private String captcha;
    private Stage thisStage;

    private Boolean changingPass = false;
    private Boolean changingSlogan = false;

    //todo get current User
    //private User currentUser = Database.getCurrentUser();
    private User currentUser = Database.getUserByUsername("jesus");

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

        userLabel.setText(currentUser.getUsername());
        userLabel.setEditable(false);
        nickNameLabel.setText(currentUser.getNickname());
        nickNameLabel.setEditable(false);
        emailLabel.setText(currentUser.getEmail());
        emailLabel.setEditable(false);
        if (currentUser.getSlogan() != null)
           sloganLabel.setText(currentUser.getSlogan());
        else
            sloganLabel.setText("Slogan is Empty!");
        sloganLabel.setEditable(false);

        //load captcha
        captcha = RandomCaptcha.generateString();
        Image captchaImage = SwingFXUtils.toFXImage(RandomCaptcha.generateImage(captcha), null);
        captchaView.setImage(captchaImage);

        //load avatar
        String path = String.valueOf(LoginMenu.class.getResource(currentUser.getAvatarPath()));
        Image avatar = new Image(path);
        avatarView.setImage(avatar);
    }


    public void profileMenu() {
        borderPane.setCenter(anchorPane);
    }

    public void avatarMenu() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        File selectedFile = fileChooser.showOpenDialog(thisStage);
        if (selectedFile != null) {
            //copy file
            String profileImagePath = selectedFile.getAbsolutePath();
            String resourcesPath = "src/main/resources/images/avatars/";
            Path source = Paths.get(profileImagePath);
            Path destination = Paths.get(resourcesPath + source.getFileName());
            if (!Files.exists(destination)){
                Files.copy(source, destination);
            }

            //set image and initialize checkboxes
            Image profileImage = new Image(selectedFile.toURI().toString());
            String imagePath = "/images/avatars/" + source.getFileName();
            currentUser.setAvatarPath(imagePath);
            avatarView.setImage(profileImage);
        }
    }

    public void scoreBoard() {
        borderPane.setCenter(new RankScroll().getScrollPane());
    }

    public void goToMainMenu(MouseEvent mouseEvent) {
    }

    public void changePass() {
        changingPass = true;

        oldPass.setVisible(true);
        newPass.setVisible(true);
        confirmPass.setVisible(true);

        captchaView.setVisible(true);
        captchaField.setVisible(true);
        resetHyper.setVisible(true);

        passChangeButton.setVisible(false);
        sloganChangeButton.setVisible(false);


        newPass.textProperty().addListener((observable, oldText, newText) -> {
            if (changingPass){
                if(newText.length() < 6 ){
                    messageLabel.setText("Your Password must be more than 5 characters!");
                    saveButton.setVisible(false);
                }
                else if (newText.equals("")) {
                    messageLabel.setText("Password field is empty!");
                    saveButton.setVisible(false);
                }
                else if(CheckFunctions.checkPasswordFormat(newText)){
                    messageLabel.setText("This Password format is invalid!");
                    saveButton.setVisible(false);
                }
                else if (confirmPass.getText().isEmpty() || oldPass.getText().isEmpty() || captchaField.getText().isEmpty()) {
                    messageLabel.setText("One field is empty!");
                    saveButton.setVisible(false);
                }
                else{
                    messageLabel.setText(null);
                    saveButton.setVisible(true);
                }
            }
        });
        confirmPass.textProperty().addListener((observable, oldText, newText) -> {
            if (changingPass){
                if(newPass.getText().isEmpty() || oldPass.getText().isEmpty() || captchaField.getText().isEmpty()) {
                    messageLabel.setText("One field is empty!");
                    saveButton.setVisible(false);
                }
                else{
                    if (!newText.equals(newPass.getText())){
                        messageLabel.setText("The given passwords don't match!");
                        saveButton.setVisible(false);
                    }
                    else{
                        messageLabel.setText(null);
                        saveButton.setVisible(true);
                    }

                }
            }
        });
        captchaField.textProperty().addListener((observable, oldText, newText) -> {
            if (changingPass){
                if (!newText.equals(captcha)){
                    messageLabel.setText("The captcha is invalid!");
                    saveButton.setVisible(false);
                }
                else if (newPass.getText().isEmpty() || oldPass.getText().isEmpty() || captchaField.getText().isEmpty()) {
                    messageLabel.setText("One field is empty!");
                    saveButton.setVisible(false);
                }
                else{
                    messageLabel.setText(null);
                    saveButton.setVisible(true);
                }
            }
        });


    }

    public void changeSlogan() {
        changingSlogan = true;

        newSloganField.setVisible(true);
        newSloganField.setText(currentUser.getSlogan());

        captchaView.setVisible(true);
        captchaField.setVisible(true);
        resetHyper.setVisible(true);

        passChangeButton.setVisible(false);
        sloganChangeButton.setVisible(false);


        captchaField.textProperty().addListener((observable, oldText, newText) -> {
            if(changingSlogan){
                if (!newText.equals(captcha)){
                    messageLabel.setText("The captcha is invalid!");
                    saveButton.setVisible(false);
                }
                else{
                    messageLabel.setText(null);
                    saveButton.setVisible(true);
                }
            }
        });

    }

    public void save() {
        if (!newPass.getText().isEmpty()){
            String newPassword = newPass.getText();
            currentUser.setPassword(newPassword);

            if(currentUser.checkPassword(oldPass.getText())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Successful!");
                alert.setHeaderText("Change Info");
                alert.setContentText("Password was changed successfully");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Change Info");
                alert.setContentText("Invalid old password!");
                alert.showAndWait();
            }

            changingPass = false;

            oldPass.setText(null);
            newPass.setText(null);
            confirmPass.setText(null);
            captchaField.setText(null);
            resetCaptcha();
        }
        else {
            String newSlogan = newSloganField.getText();
            currentUser.setSlogan(newSlogan);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful!");
            alert.setHeaderText("Change Info");
            alert.setContentText("Slogan was changed successfully");
            alert.showAndWait();

            if (currentUser.getSlogan() != null)
                sloganLabel.setText(currentUser.getSlogan());
            else
                sloganLabel.setText("Slogan is Empty!");
            sloganLabel.setEditable(false);

            changingSlogan = false;

            newSloganField.setText(null);
            captchaField.setText(null);
            resetCaptcha();
        }

        oldPass.setVisible(false);
        newPass.setVisible(false);
        confirmPass.setVisible(false);

        newSloganField.setVisible(false);

        captchaView.setVisible(false);
        captchaField.setVisible(false);
        resetHyper.setVisible(false);
        sloganChangeButton.setVisible(true);
        passChangeButton.setVisible(true);
    }

    public void resetCaptcha() {
        captcha = RandomCaptcha.generateString();
        Image captchaImage = SwingFXUtils.toFXImage(RandomCaptcha.generateImage(captcha), null);
        captchaView.setImage(captchaImage);
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
        thisStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
