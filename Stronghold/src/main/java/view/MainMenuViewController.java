package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainMenuViewController extends Application {

    private BorderPane borderPane;
    private int width;
    private int height;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        width = (int) Screen.getPrimary().getBounds().getWidth();
        height = (int) Screen.getPrimary().getBounds().getHeight();


        borderPane = new BorderPane();
        borderPane.setPrefSize(width, height);

        setBackground();

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }

    private void setBackground(){
        Image image = new Image(
                MainMenuViewController.class.getResource("/images/backgrounds/main-menu-background.jpg").toExternalForm()
        );




        BackgroundImage backgroundFill = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        );

        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);
    }
}
