package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameMenuApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        stage.setFullScreen(true);

        stage.setScene(GameViewController.getScene());
        stage.show();
    }
}
