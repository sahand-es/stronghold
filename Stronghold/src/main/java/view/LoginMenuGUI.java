package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenuGUI extends Application {

    public void login(MouseEvent mouseEvent) {
    }

    public void signUp(MouseEvent mouseEvent) {
    }

    public void forgotPassword(MouseEvent mouseEvent) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenu.class.getResource("/fxml/login-menu.fxml");
        assert url != null;
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane,520,400);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
