package view;

import controller.GameControl;
import javafx.animation.Animation.Status;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Database;
import model.environment.buildings.Building;
import model.map.Block;
import model.map.Texture;
import model.units.Person;
import utility.DataManager;
import view.animation.MoveAnimation;
import view.fxmlcontroller.BuildingScroll;
import view.shape.BuildingNode;
import view.shape.Fire;
import view.shape.PersonNode;
import view.shape.map.MapPane;
import view.shape.map.MapTile;
import view.shape.map.MiniMap;

import java.io.IOException;
import java.util.Objects;
import java.util.Queue;

public class GameViewController {
    private static Scene scene;
    private static Pane mainPane;
    private static MapPane mapPane;
    private static MiniMap miniMap;
    private static ScrollPane mapTextureOptions;
    private static TabPane buildingScroll;
    private static Pane personControl;
    private static Group allPersons;
    private static Group allBuildings;

    public static void setMapPane(MapPane mapPane, Pane mainPane, Scene scene, MiniMap miniMap) throws IOException {
        GameViewController.mapPane = mapPane;
        GameViewController.mainPane = mainPane;
        GameViewController.scene = scene;
        GameViewController.miniMap = miniMap;
        mapTextureOptions = FXMLLoader.load(Objects.requireNonNull(MapPane.class.getResource(DataManager.CHANGE_TEXTURE_BOX)));
        buildingScroll = FXMLLoader.load(Objects.requireNonNull(BuildingScroll.class.getResource("/fxml/building-scroll.fxml")));
        allPersons = new Group();
        allBuildings = new Group();
        setKeys();

        mapPane.getChildren().addAll(allBuildings, allPersons);
    }

    private static void setKeys() {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if (keyEvent.getCode().equals(KeyCode.T)) {
                    addNode(mapTextureOptions, Database.centerX - mapTextureOptions.getPrefWidth() / 2,
                            Database.centerY - mapTextureOptions.getPrefHeight() / 2);
                } else if (keyEvent.getCode().equals(KeyCode.R)) {
                    mapPane.reset();
                } else if (keyEvent.getCode().equals(KeyCode.V)) {
                    if (Database.getCopyBuilding() != null) {
                        Block block = mapPane.getSelectedTiles().get(0).getBlock();
                        GameControl.dropBuilding(block.getX(), block.getY(), Database.getCopyBuilding());
                    }
                } else if (keyEvent.getCode().equals(KeyCode.M)) {
                    removeNode(personControl);
                    try {
                        personControl = FXMLLoader.load(this.getClass().getResource("/fxml/soldier-control-box.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    addNode(personControl, Database.centerX - personControl.getPrefWidth() / 2,
                            Database.centerY - personControl.getPrefHeight() / 2);
                } else {
                    mainPane.getChildren().remove(mapTextureOptions);
                    mainPane.getChildren().remove(personControl);
                }
            }
        });
    }

    public static void addNode(Node node, double x, double y) {
        if (!mainPane.getChildren().contains(node)) {
            mainPane.getChildren().add(node);
            node.setLayoutX(x);
            node.setLayoutY(y);
        }
    }

    public static void removeNode(Node node) {
        mainPane.getChildren().remove(node);
    }

    public static void addUnit(Person person) {
        PersonNode pn = new PersonNode(person);
        pn.setLayoutX(getLayoutXForPerson(person.getBlock().getTile()));
        pn.setLayoutY(getLayoutYForPerson(person.getBlock().getTile()));
        allPersons.getChildren().add(pn);
    }

    public static double getLayoutXForPerson(MapTile tile) {
        return tile.getLayoutX() + MapTile.TILE_WIDTH * 0.1 + tile.getBlock().getUnits().size() * 3;
    }

    public static double getLayoutYForPerson(MapTile tile) {
        return tile.getLayoutY() -
                MapTile.TILE_HEIGHT * 0.3 + tile.getBlock().getUnits().size() * 1.5;
    }

    public static PersonNode getPersonNodeByPerson(Person person) {
        for (Node child : allPersons.getChildren()) {
            if (!(child instanceof PersonNode))
                return null;
            PersonNode pn = (PersonNode) child;
            if (pn.getPerson().equals(person))
                return pn;
        }
        return null;
    }

    public static BuildingNode getBuildingNodeByBuilding(Building building) {
        for (Node child : allBuildings.getChildren()) {
            if (!(child instanceof BuildingNode))
                return null;
            BuildingNode bn = (BuildingNode) child;
            if (bn.getBuilding().equals(building))
                return bn;
        }
        return null;
    }

    public static void moveUnit(Person person, Queue<Block> movedQueue) {
        if (movedQueue.isEmpty())
            return;
        MapTile toTile = movedQueue.poll().getTile();
        PersonNode pn = getPersonNodeByPerson(person);
        if (pn == null)
            return;

        MoveAnimation ma = new MoveAnimation(pn, toTile);
        ma.play();
        ma.statusProperty().addListener(new ChangeListener<Status>() {

            @Override
            public void changed(ObservableValue<? extends Status> observableValue,
                                Status oldValue, Status newValue) {
                if (newValue == Status.STOPPED) {
                    if (movedQueue.isEmpty())
                        return;
                    ma.changeDestination(movedQueue.poll().getTile());
                    ma.play();
                }
            }
        });
    }

    public static void selectUnit() {

    }

    public static void addBuilding(Building building) {
        BuildingNode bn = new BuildingNode(building);
        bn.setLayoutX(building.getBlock().getTile().getLayoutX());
        bn.setLayoutY(building.getBlock().getTile().getLayoutY() - bn.getHeight() / 2);
        allBuildings.getChildren().add(bn);
    }

    public static void removeBuilding(Building building) {
        allBuildings.getChildren().remove(getBuildingNodeByBuilding(building));
    }

    public static void removePerson(Person person) {
        allPersons.getChildren().remove(getPersonNodeByPerson(person));
    }

    public static void setFire(Building building) {
        BuildingNode bn = getBuildingNodeByBuilding(building);

        Fire fire = new Fire();
        fire.setLayoutX(bn.getLayoutX() - MapTile.TILE_WIDTH / 4);
        fire.setLayoutY(bn.getLayoutY() - MapTile.TILE_HEIGHT);

        allBuildings.getChildren().add(fire);
    }

    public static void setTexture(Texture texture) {
        mapPane.setTexture(texture);
        miniMap.setTexture(texture, mapPane.getSelectedTiles());
        // TODO: 6/26/2023 minimap change
    }

    public static MapPane getMapPane() {
        return mapPane;
    }
}

