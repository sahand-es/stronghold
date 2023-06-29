package model;

import controller.GameControl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.map.Map;
import model.society.Government;
import view.GameViewController;
import view.shape.government.ControlPanel;
import view.shape.map.MapPane;

public class GUITest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        stage.setFullScreen(true);


        Map map = new Map(50, 50);
        MapPane mapPane = new MapPane(map);



        Scene scene;

        Game game = new Game(map, 2);
        Government g1 = game.getGovernments().get(0);
        Government g2 = game.getGovernments().get(1);
        Database.setCurrentGame(game);

        GameControl.setGame(game);

        stage.setScene(GameViewController.getScene());
        stage.show();
    }
}
