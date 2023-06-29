package model;

import controller.GameControl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.map.Map;
import model.society.Government;
import model.units.enums.UnitName;
import view.GameViewController;
import view.fxmlcontroller.BuildingScroll;
import view.shape.government.ControlPanel;
import view.shape.map.MapPane;
import view.shape.map.MiniMap;

import java.util.Objects;
import java.util.TimerTask;

public class GUITest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
//        stage.setFullScreen(true);

        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, new CornerRadii(0), new Insets(0))));

        Map map = new Map(50, 50);
        MapPane mapPane = new MapPane(map);



        Scene scene = new Scene(pane);

        pane.getChildren().add(mapPane);

        Game game = new Game(map, 2);
        Government g1 = game.getGovernments().get(0);
        Government g2 = game.getGovernments().get(1);
        Database.setCurrentGame(game);

        GameControl.setGame(game);
        ControlPanel controlPanel = new ControlPanel();
        GameViewController.setMapPane(mapPane, pane, scene, controlPanel);

        stage.setScene(scene);
        stage.show();
    }
}
