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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.map.Map;
import model.society.Government;
import model.units.enums.UnitName;
import view.GameViewController;
import view.fxmlcontroller.BuildingScroll;
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
        MiniMap miniMap = new MiniMap(map);

        TabPane buildingScroll = FXMLLoader.load(Objects.requireNonNull(
                BuildingScroll.class.getResource("/fxml/building-scroll.fxml")));

        Scene scene = new Scene(pane);

        pane.getChildren().add(mapPane);

        pane.getChildren().add(miniMap);
        miniMap.setLayoutX(610);
        miniMap.setLayoutY(630);
        GameViewController.setMapPane(mapPane, pane, scene, miniMap);
        GameViewController.addNode(buildingScroll, 0, 650);
        Game game = new Game(map, 2);
        Government g1 = game.getGovernments().get(0);
        Government g2 = game.getGovernments().get(1);

        GameControl.setGame(game);
//        GameControl.dropUnit(15, 15, UnitName.SLAVE.getName(), 1);
//
//        GameControl.dropBuilding(3, 3, "church");
//        GameControl.nextTurn();
//        GameControl.dropUnit(6, 6, UnitName.ARABIAN_BOW.getName(), 1);
//        GameControl.dropUnit(6, 6, UnitName.KNIGHT.getName(), 1);
//        GameControl.dropUnit(6, 8, UnitName.TUNNELER.getName(), 1);


//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
//            System.out.println(game.getCurrentGovernment());
//            GameControl.nextTurn();
//        }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();

//

//
//        GameControl.selectUnit(5,10, 1);
//        GameControl.setSoldierState("defencive");


//        Building building = new Building(BuildingName.LOOKOUT_TOWER,
//                new Government(Colors.BLACK_COLOR, game), map.getBlockByXY(4, 4));
//
//        GameViewController.addBuilding(building);
//        GameViewController.setFire(building);

////


//        Circle c = new  Circle(2);
//        c.setCenterX(map.getBlockByXY(2,2).getTile().getLayoutX());
//        c.setCenterY(map.getBlockByXY(2,2).getTile().getLayoutY());
//        c.setFill(Color.WHITE);

//        mapPane.getChildren().add(c);


        stage.setScene(scene);
        stage.show();
    }
}
