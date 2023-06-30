package view.GUIController;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GameViewController;

public class GameMenuApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(GameViewController.getScene());
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }
}
