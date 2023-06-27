package view.fxmlcontroller;

import controller.GameControl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.environment.buildings.Building;
import model.environment.buildings.enums.BuildingName;
import view.GameViewController;
import view.shape.BuildingNode;
import view.shape.map.MapPane;
import view.shape.map.MapTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BuildingScroll {
    public HBox hBox;
    public TabPane tabs;

    @FXML
    private void initialize() {
        Set<String> kinds = new HashSet<>();
        for (Tab tab : tabs.getTabs()) {
            kinds.add(tab.getText());
        }
        for (Tab tab : tabs.getTabs()) {
            ScrollPane scrollPane = (ScrollPane) tab.getContent();
            HBox hBoxTab = (HBox) scrollPane.getContent();

            for (BuildingName name : BuildingName.values()) {
                if (kinds.contains(name.kind) && name.kind.equalsIgnoreCase(tab.getText())) {
                    setHboxForBuildings(hBoxTab, name);
                } else if (!kinds.contains(name.kind) && tab.getText().equalsIgnoreCase("Other")) {
                    setHboxForBuildings(hBoxTab, name);
                }
            }
        }


    }

    private void setHboxForBuildings(HBox hBoxTab, BuildingName name) {
        VBox buildingVBox = new VBox();
        buildingVBox.setPrefHeight(150);
        buildingVBox.setPrefWidth(100);
        BuildingNode bn = new BuildingNode(new Building(name), 0);
        buildingVBox.getChildren().add(bn);
        buildingVBox.setSpacing(8);
        buildingVBox.setAlignment(Pos.CENTER);
        Text text = new Text(name.getName());

        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(buildingVBox.getPrefWidth());
        buildingVBox.getChildren().add(text);
        hBoxTab.getChildren().add(buildingVBox);

        BuildingNode bnToMove = new BuildingNode(new Building(name), 0);

        bn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()) {
                    GameViewController.addNode(bnToMove, mouseEvent.getSceneX() - 40, mouseEvent.getSceneY() - 50);
                    bnToMove.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                                bnToMove.setX(mouseEvent.getX());
                                bnToMove.setY(mouseEvent.getY());
                        }
                    });

                    bnToMove.setOnMouseReleased(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            GameViewController.removeNode(bnToMove);
                            ArrayList<MapTile> intersectedTiles = new ArrayList<>();
                            MapPane mapPane = GameViewController.getMapPane();
                            for (Node node : mapPane.getAllTiles().getChildren()) {
                                MapTile tile = (MapTile) node;
//                                bnToMove.setX(mapPane.getLayoutX());
//                                bnToMove.setY(mapPane.getLayoutY());

                                double x = tile.getLayoutX();
                                double y = tile.getLayoutY();
                                tile.setLayoutX(x + mapPane.getLayoutX());
                                tile.setLayoutY(y + mapPane.getLayoutY());
//                                Bounds bound2 = tile.getBoundsInParent();
                                if (bnToMove.getBoundsInParent().intersects(tile.getBoundsInParent())) {
                                    intersectedTiles.add(tile);
                                }
                                tile.setLayoutX(x);
                                tile.setLayoutY(y);
                            }

                            if (intersectedTiles.isEmpty()) return;

                            Collections.sort(intersectedTiles, (first, second) -> {
                                return (int) -(first.getLayoutY() - second.getLayoutY());
                            });


                            GameControl.dropBuilding(intersectedTiles.get(0).getBlock().getX(), intersectedTiles.get(0).getBlock().getY(), bnToMove.getBuilding().getName().getName());
                        }
                    });
                }
            }
        });
    }
}
